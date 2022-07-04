package com.example.news.ui.newsList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.News
import com.example.news.data.repository.NewsListRepository
import com.example.news.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ParallelSeriesNewsListViewModel(private val repository: NewsListRepository ) : ViewModel() {

     private val newsList = MutableLiveData<Resource<List<News>>>()

    init{
        fetchNews()
    }

    private fun fetchNews(){
    viewModelScope.launch{
        newsList.postValue(Resource.loading(null))
        try {
            coroutineScope {
                val response = async { repository.getNews() }
                val moreResponse = async { repository.getMoreNews() }
                val allResponse = mutableListOf<News>()
                allResponse.addAll(response.await())
                allResponse.addAll(moreResponse.await())
                newsList.postValue(Resource.success(allResponse))
            }
        }
        catch (e : Exception){
            newsList.postValue(Resource.error(null,e.toString()))
        }
    }

    }


     fun getNews() : LiveData<Resource<List<News>>>{
        return newsList
    }

}