package com.example.news.ui.newsList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.News
import com.example.news.data.repository.NewsListRepository
import com.example.news.utils.Resource
import kotlinx.coroutines.launch

class NewsListViewModel(private val repository: NewsListRepository ) : ViewModel() {

     private val newsList = MutableLiveData<Resource<List<News>>>()

    init{
        fetchNews()
    }

    private fun fetchNews(){
    viewModelScope.launch{
        newsList.postValue(Resource.loading(null))
        try{
        val response = repository.getNews()
        newsList.postValue(Resource.success(response))
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