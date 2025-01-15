package com.example.uts_pemrograman_bergerak.api

import com.example.uts_pemrograman_bergerak.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("/news")
    fun getNewsList(): Call<NewsResponse>
}
