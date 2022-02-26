package com.example.kaloricketabulkylite.data.local.dao.search

import androidx.room.Dao
import androidx.room.Query
import com.example.kaloricketabulkylite.data.local.dao.BaseDao
import com.example.kaloricketabulkylite.data.local.entity.search.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SearchDao : BaseDao<SearchEntity>() {
    @Query("SELECT * FROM SearchEntity")
    abstract fun getAllPotravinaEntityFlow(): Flow<List<SearchEntity>>

    @Query("SELECT * FROM SearchEntity WHERE nazev LIKE '%' || :query || '%'")
    abstract fun getPotravinaEntityListByQueryFlow(query: String): Flow<List<SearchEntity>>
}