package com.example.uts_pemrograman_bergerak.model

data class NewsItem(
    val id: Int,
    val headline: String,
    val abstract: String,
    val body: String,
    val author: String,
    val section: String,
    val date: String,
    val articleUri: String,
    val pdfUri: String,
    val image_url: String
)

data class NewsResponse(
    val news: List<NewsItem>
)

data class MovieItem(
    val id: Int,
    val movie: String,
    val rating: Double,
    val image: String,
    val imdb_url: String
)


