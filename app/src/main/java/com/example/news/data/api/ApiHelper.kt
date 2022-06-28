package com.example.news.data.api

import com.example.news.data.model.News

interface ApiHelper {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>


}