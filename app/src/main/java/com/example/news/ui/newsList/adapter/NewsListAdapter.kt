package com.example.news.ui.newsList.adapter

import android.content.ClipData
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.model.News
import com.example.news.databinding.ItemLayoutBinding

class NewsListAdapter(private var newsList : MutableList<News>) :
    RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class DataViewHolder(val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(news : News){
            with(binding) {
                tvDesc.text = news.description
                tvHeading.text = news.title
                tvSource.text = news.source
                Glide.with(binding.root).load(news.imageUrl).into(
                    iv
                )
                itemView.setOnClickListener {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(it.context, Uri.parse(news.url))
                }
            }
        }
    }

    fun addData(list: List<News>){
        newsList.clear()
        newsList.addAll(list)
        this.notifyDataSetChanged()
    }
}