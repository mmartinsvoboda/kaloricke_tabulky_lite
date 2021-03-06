package com.example.kaloricketabulkylite.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kaloricketabulkylite.data.local.dao.potravina.PotravinaDao
import com.example.kaloricketabulkylite.data.local.dao.search.SearchDao
import com.example.kaloricketabulkylite.data.local.entity.potravina.PotravinaEntity
import com.example.kaloricketabulkylite.data.local.entity.search.SearchEntity

@Database(
    entities = [
        SearchEntity::class,
        PotravinaEntity::class
    ],
    exportSchema = false,
    version = 2271423 // MddHHmm
)
abstract class KalorickeTabulkyLiteDatabase : RoomDatabase() {

    abstract fun getSearchDao(): SearchDao
    abstract fun getPotravinaDao(): PotravinaDao

    companion object {
        @Volatile
        private var instance: KalorickeTabulkyLiteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                KalorickeTabulkyLiteDatabase::class.java,
                "uisDatabase.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}