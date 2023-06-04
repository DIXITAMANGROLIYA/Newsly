package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
//import javax.security.auth.callback.Callback

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }

    private fun getNews() {
        val news: Call<News> = NewsService.newsInterface.getHeadline("in")//1
        news.enqueue(object : retrofit2.Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.e("TAG", "onResponse: "+response.raw().toString() )
                val news = response.body()
                if (news != null) {
                    Log.d("practice", news.toString())
                    adapter=NewsAdapter(this@MainActivity, news.articles)

                    val newsList = findViewById<RecyclerView>(R.id.newsList)
                    newsList.adapter= adapter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Practice", "Error in Fetching News", t)

            }
        })

    }
}

