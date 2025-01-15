package com.example.uts_pemrograman_bergerak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uts_pemrograman_bergerak.databinding.ActivityItemNewsBinding
import com.example.uts_pemrograman_bergerak.model.NewsItem

class NewsAdapter(private val newsList: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ActivityItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.bind(newsItem)
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(private val binding: ActivityItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.title.text = newsItem.headline
            binding.description.text = newsItem.abstract
//            Glide.with(binding.root.context).load("https://grist.org/wp-content/uploads/2015/11/organic.jpg").into(binding.imageView)
            Glide.with(binding.root.context).load(newsItem.image_url).into(binding.imageView)
        }
    }
}
