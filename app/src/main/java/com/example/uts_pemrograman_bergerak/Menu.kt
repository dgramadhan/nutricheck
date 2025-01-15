package com.example.uts_pemrograman_bergerak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uts_pemrograman_bergerak.api.RetrofitClient
import com.example.uts_pemrograman_bergerak.databinding.ActivityMenuBinding
import com.example.uts_pemrograman_bergerak.util.NetworkUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts_pemrograman_bergerak.adapter.NewsAdapter
import com.example.uts_pemrograman_bergerak.model.NewsItem
import com.example.uts_pemrograman_bergerak.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (NetworkUtils.isNetworkAvailable(this)) {
            fetchNews()
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }

        enableEdgeToEdge()
//        setContentView(R.layout.activity_menu)

        val buttonSubmit = findViewById<Button>(R.id.btn_kalkulator)
        buttonSubmit.setOnClickListener() {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun fetchNews() {
        RetrofitClient.apiService.getNewsList().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.news ?: emptyList()
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@Menu)
                    binding.recyclerView.adapter = NewsAdapter(newsList)
                } else {
                    // Handle the case when the response is not successful
                    Log.e("NewsApi", "Failed to get news: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure, e.g., no internet connection
                Log.e("NewsApi", "Error: ${t.message}")
            }
        })
    }
}