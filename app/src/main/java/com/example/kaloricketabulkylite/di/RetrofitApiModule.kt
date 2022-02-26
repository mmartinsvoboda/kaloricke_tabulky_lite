package com.example.kaloricketabulkylite.di

import android.content.Context
import com.example.kaloricketabulkylite.data.remote.RetrofitApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofitApiService(
        @ApplicationContext appContext: Context
    ): RetrofitApiService {
        return RetrofitApiService(appContext)
    }

}