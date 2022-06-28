package com.example.news.data.api

import com.example.news.data.model.News

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNews(): List<News> {
        return apiService.getNews()
    }

    override suspend fun getMoreNews(): List<News> {
        return apiService.getMoreNews()
    }


}