package com.example.kaloricketabulkylite.data.repository.search

import com.example.kaloricketabulkylite.data.local.KalorickeTabulkyLiteDatabase
import com.example.kaloricketabulkylite.data.remote.RetrofitApiService
import com.example.kaloricketabulkylite.utils.data.BaseDataSource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    localDataSource: KalorickeTabulkyLiteDatabase,
    private val remoteDataSource: RetrofitApiService
) : BaseDataSource() {
    private val dao = localDataSource.getSearchDao()

    suspend fun getSearchPotraviny(
        query: String
    ) = getResult("getSearchPotraviny") {
        remoteDataSource.getSearchPotraviny(query = query)
    }
}