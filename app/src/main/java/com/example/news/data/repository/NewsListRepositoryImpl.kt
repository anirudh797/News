package com.example.news.data.repository

import com.example.news.data.api.ApiHelper
import com.example.news.data.api.ApiHelperImpl
import com.example.news.data.model.News

class NewsListRepositoryImpl(private val apiHelper: ApiHelperImpl) : NewsListRepository {

    override suspend fun getNews(): List<News> = apiHelper.getNews()

    override suspend fun getMoreNews(): List<News> = apiHelper.getMoreNews()

}