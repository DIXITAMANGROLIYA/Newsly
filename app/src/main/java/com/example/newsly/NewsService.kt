package com.example.newsly

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?&apiKey=feeebe1f96354859bfee680dd9c703eb

const val BASE_URL ="https://newsapi.org/"
const val API_KEY ="feeebe1f96354859bfee680dd9c703eb"

interface NewsInterface{
     @GET("v2/top-headlines?apiKey=$API_KEY")
     fun getHeadline(@Query("country")country : String) : retrofit2.Call<News>
}                //, @Query("page")page: Int

object NewsService{
    val newsInterface : NewsInterface
     init {
         val retrofit = Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
          newsInterface = retrofit.create(NewsInterface::class.java)
     }
}