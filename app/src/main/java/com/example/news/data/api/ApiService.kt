package com.example.news.data.api

import com.example.news.data.model.News
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("news")
    suspend fun getNews(): List<News>

    @GET("more-news")
    suspend fun getMoreNews(): List<News>


}

object RetrofitBuilder {

    private const val BASE_URL = "https://demo9376629.mockable.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}