package com.example.news.ui.newsList.view

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.data.api.ApiHelperImpl
import com.example.news.data.api.RetrofitBuilder
import com.example.news.data.model.News
import com.example.news.databinding.ActivityNewsListBinding
import com.example.news.ui.base.ViewModelFactory
import com.example.news.ui.newsList.adapter.NewsListAdapter
import com.example.news.ui.newsList.viewModel.NewsListViewModel
import com.example.news.ui.newsList.viewModel.ParallelSeriesNewsListViewModel
import com.example.news.ui.newsList.viewModel.SeriesNewsListViewModel
import com.example.news.utils.Status

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var seriesNewsListViewModel: SeriesNewsListViewModel
    private lateinit var parallelNewsListViewModel: ParallelSeriesNewsListViewModel

    private lateinit var adapter: NewsListAdapter
    lateinit var binding : ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        setupUi()
        setupObserver()
    }



    fun setupUi(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(arrayListOf())
        (binding.recyclerView.layoutManager as? LinearLayoutManager)?.orientation?.let {
            DividerItemDecoration(this,
                it
            )
        }?.let {
            binding.recyclerView.addItemDecoration(
                it
            )
        }
        binding.recyclerView.adapter = adapter
    }

    fun setupObserver(){

        parallelNewsListViewModel.getNews().observe(this) {
            when (it.status) {
                    Status.SUCCESS -> {
                        binding.pb.visibility = View.GONE
                        it?.data?.let {
                            renderList(it)
                        }
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        binding.pb.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }

                    Status.ERROR -> {
                        binding.pb.visibility = View.GONE
                        Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    }

            }
        }

    }

    private fun renderList(newsList : List<News>){
        adapter.addData(newsList)

    }

    private fun setupViewModel(){
        parallelNewsListViewModel = ViewModelProviders.of(this,ViewModelFactory(ApiHelperImpl(
            RetrofitBuilder.apiService
        ))).get(ParallelSeriesNewsListViewModel::class.java)
    }
}