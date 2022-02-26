package com.example.kaloricketabulkylite.data.local.dao.potravina

import androidx.room.Dao
import androidx.room.Query
import com.example.kaloricketabulkylite.data.local.dao.BaseDao
import com.example.kaloricketabulkylite.data.local.entity.potravina.PotravinaEntity
import com.example.kaloricketabulkylite.data.local.entity.search.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PotravinaDao : BaseDao<PotravinaEntity>() {
    @Query("SELECT * FROM PotravinaEntity")
    abstract fun getAllPotravinaEntityFlow(): Flow<List<PotravinaEntity>>

    @Query("SELECT * FROM PotravinaEntity WHERE guidPotravina = :guidPotravina")
    abstract fun getPotravinaEntityListByQueryFlow(guidPotravina: String): Flow<PotravinaEntity?>
}