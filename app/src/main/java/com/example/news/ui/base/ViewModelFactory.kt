package com.example.news.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.data.api.ApiHelper
import com.example.news.data.api.ApiHelperImpl
import com.example.news.data.repository.NewsListRepositoryImpl
import com.example.news.ui.newsList.viewModel.NewsListViewModel
import com.example.news.ui.newsList.viewModel.ParallelSeriesNewsListViewModel
import com.example.news.ui.newsList.viewModel.SeriesNewsListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelperImpl) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsListViewModel::class.java)){
            return NewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        if(modelClass.isAssignableFrom(SeriesNewsListViewModel::class.java)){
            return SeriesNewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        if(modelClass.isAssignableFrom(ParallelSeriesNewsListViewModel::class.java)){
            return ParallelSeriesNewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }


        throw IllegalArgumentException("Unknown Class name")
    }


}