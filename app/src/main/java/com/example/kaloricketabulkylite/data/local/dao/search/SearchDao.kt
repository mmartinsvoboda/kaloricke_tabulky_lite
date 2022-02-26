package com.example.kaloricketabulkylite.data.local.dao.search

import androidx.room.Dao
import androidx.room.Query
import com.example.kaloricketabulkylite.data.local.dao.BaseDao
import com.example.kaloricketabulkylite.data.local.entity.search.PotravinaEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SearchDao : BaseDao<PotravinaEntity>() {
    @Query("SELECT * FROM PotravinaEntity")
    abstract fun getAllPotravinaEntityFlow(): Flow<List<PotravinaEntity>>

    @Query("SELECT * FROM PotravinaEntity WHERE nazev LIKE '%' || :query || '%'")
    abstract fun getPotravinaEntityListByQueryFlow(query: String): Flow<List<PotravinaEntity>>
}