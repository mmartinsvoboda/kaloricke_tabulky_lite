package com.example.kaloricketabulkylite.data.repository.potravina

import com.example.kaloricketabulkylite.data.local.KalorickeTabulkyLiteDatabase
import com.example.kaloricketabulkylite.data.remote.RetrofitApiService
import com.example.kaloricketabulkylite.utils.data.BaseDataSource
import com.example.kaloricketabulkylite.utils.data.performGetOperation
import com.example.kaloricketabulkylite.utils.data.syncerForEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PotravinaRepository @Inject constructor(
    localDataSource: KalorickeTabulkyLiteDatabase,
    private val remoteDataSource: RetrofitApiService
) : BaseDataSource() {
    val dao = localDataSource.getPotravinaDao()

    fun getPotravina(
        GUID_Potravina: String,
        shouldFetchFromNetwork: Boolean = true
    ) = performGetOperation(
        fetchFromLocal = {
            dao.getPotravinaEntityListByQueryFlow(GUID_Potravina)
        },
        fetchFromRemote = {
            getResult("getPotravina") {
                remoteDataSource.getPotravina(GUID_Potravina)
            }
        },
        shouldFetchFromNetwork = {
            shouldFetchFromNetwork || it == null
        },
        syncRemoteData = { local, remote ->
            syncerForEntity(
                baseDao = dao,
                entityToKey = { it.guidPotravina }
            ).sync(
                current = local,
                network = remote,
                syncValues = { db, remote ->
                    remote.oblibena = db.oblibena
                    remote
                }
            )
        }
    )
}

