package com.example.news.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.data.api.ApiHelper
import com.example.news.data.api.ApiHelperImpl
import com.example.news.data.repository.NewsListRepositoryImpl
import com.example.news.ui.newsList.viewModel.NewsListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelperImpl) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsListViewModel::class.java)){
            return NewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown Class name")
    }


}