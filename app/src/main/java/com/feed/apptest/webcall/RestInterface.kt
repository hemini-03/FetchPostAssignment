package com.feed.apptest.webcall

import com.feed.apptest.model.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RestInterface {

    //Search by date API
    @GET("search_by_date")
    fun searchData(@Query("tags") tags: String, @Query("page") page: Int): Call<PostModel>

}