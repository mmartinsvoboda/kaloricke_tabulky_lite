package com.example.kaloricketabulkylite.utils.data

import com.example.kaloricketabulkylite.data.local.dao.BaseDao
import timber.log.Timber

class ItemSyncer<DB, Key>(
    private val insertEntity: suspend (DB) -> Long,
    private val updateEntity: suspend (DB) -> Unit,
    private val deleteEntity: suspend (DB) -> Int,
    private val entityToKey: suspend (DB) -> Key
) {
    suspend fun sync(
        current: Collection<DB>,
        network: Collection<DB>,
        syncValues: (DB, DB) -> DB = { db, remote -> remote },
        removeNotMatched: Boolean = true
    ): ItemSyncerResult<DB> {
        val currentDbEntities = ArrayList(current)

        val removed = ArrayList<DB>()
        val added = ArrayList<DB>()
        val updated = ArrayList<DB>()

        for (networkEntity in network) {
            Timber.v("Syncing item from network: %s", networkEntity)

            val remoteId = entityToKey(networkEntity)
            Timber.v("Mapped to remote ID: %s", remoteId)
            if (remoteId == null) {
                break
            }

            val dbEntityForId = currentDbEntities.find {
                entityToKey(it) == remoteId
            }
            Timber.v("Matched database entity for remote ID %s : %s", remoteId, dbEntityForId)

            if (dbEntityForId != null) {
                Timber.v("Mapped network entity to local entity: %s", networkEntity)
                if (dbEntityForId != networkEntity) {
                    // This is currently in the DB, so lets merge it with the saved version
                    // and update it
                    updateEntity(syncValues(dbEntityForId, networkEntity))
                    Timber.v("Updated entry with remote id: %s", remoteId)
                }
                // Remove it from the list so that it is not deleted
                currentDbEntities.remove(dbEntityForId)
                updated += networkEntity
            } else {
                // Not currently in the DB, so lets insert
                Timber.v("Added entry with remote id: %s", remoteId)
                added += networkEntity
            }
        }

        if (removeNotMatched) {
            // Anything left in the set needs to be deleted from the database
            currentDbEntities.forEach {
                Timber.v("Deleted entry: ", it)
                deleteEntity(it)
                removed += it
            }
        }

        // Finally we can insert all of the new entities
        added.forEach {
            insertEntity(it)
        }

        Timber.v("Total items: ${network.size}\nAdded: ${added.size}\nUpdated: ${updated.size}\nDeleted: ${removed.size}")

        return ItemSyncerResult(added, removed, updated)
    }

    suspend fun sync(
        current: DB?,
        network: DB?,
        syncValues: (DB, DB) -> DB = { db, remote -> remote },
        removeNotMatched: Boolean = true
    ): ItemSyncerResult<DB> {
        val removed = ArrayList<DB>()
        val added = ArrayList<DB>()
        val updated = ArrayList<DB>()

        Timber.v("Syncing item from network: %s", network)

        if (network != null) {
            val remoteId = entityToKey(network)

            if (current != null && network != current) {
                updateEntity(syncValues(current, network))
                updated.add(network)
                Timber.v("Updated entry with remote id: %s", remoteId)
            } else {
                insertEntity(network)
                added.add(network)
                Timber.v("Added entry with remote id: %s", remoteId)
            }
        } else if (current != null && removeNotMatched) {
            Timber.v("Deleted entry: ", current)
            removed.add((current))
            deleteEntity(current)
        }

        Timber.v("Total items: 1\nAdded: ${added.size}\nUpdated: ${updated.size}\nDeleted: ${removed.size}")

        return ItemSyncerResult(added, removed, updated)
    }
}

data class ItemSyncerResult<ET>(
    val added: List<ET> = emptyList(),
    val deleted: List<ET> = emptyList(),
    val updated: List<ET> = emptyList()
)

fun <DB, Key> syncerForEntity(
    baseDao: BaseDao<DB>,
    entityToKey: suspend (DB) -> Key
) = ItemSyncer(
    baseDao::insert,
    baseDao::update,
    baseDao::deleteEntity,
    entityToKey
)