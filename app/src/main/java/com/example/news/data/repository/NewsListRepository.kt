package com.example.news.data.repository

import com.example.news.data.model.News

interface NewsListRepository {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>


}