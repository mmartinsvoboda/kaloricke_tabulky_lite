package com.example.kaloricketabulkylite.di

import android.content.Context
import com.example.kaloricketabulkylite.data.local.KalorickeTabulkyLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getKalorickeTabulkyLiteDatabase(@ApplicationContext appContext: Context): KalorickeTabulkyLiteDatabase {
        return KalorickeTabulkyLiteDatabase.invoke(appContext)
    }

}