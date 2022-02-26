package com.example.kaloricketabulkylite.data.remote

import android.content.Context
import com.example.kaloricketabulkylite.data.local.entity.potravina.PotravinaEntity
import com.example.kaloricketabulkylite.data.remote.response.search.SearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {

    @GET("getSearchPotraviny.php")
    suspend fun getSearchPotraviny(
        @Query("Q") query: String,
        @Query("pid") pid: String = "m4RSv0b1"
    ): Response<SearchResponse>

    @GET("getPotravina.php")
    suspend fun getPotravina(
        @Query("GUID_Potravina") GUID_Potravina: String,
        @Query("pid") pid: String = "m4RSv0b1"
    ): Response<PotravinaEntity>

    companion object {
        private var retrofit: Retrofit? = null

        operator fun invoke(
            appContext: Context
        ): RetrofitApiService {

            if (retrofit == null) {
                createRetrofitClient()
            }

            return retrofit!!.create(RetrofitApiService::class.java)
        }

        private fun createRetrofitClient() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.kaloricketabulky.cz/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
        }
    }
}