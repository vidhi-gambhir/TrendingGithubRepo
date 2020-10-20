package com.example.timer.retrofit

import com.example.timer.MyItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("repositories")
    fun getTrendingList():Call<List<MyItem>>
}