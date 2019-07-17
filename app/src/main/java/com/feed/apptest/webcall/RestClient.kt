package com.feed.apptest.webcall

import android.content.Context
import com.feed.apptest.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RestClient(private val context: Context) {

    private var retrofit: Retrofit? = null
    private fun getRetrofit(): Retrofit {


        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()



        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.PATH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()



        return retrofit!!

    }


    fun getApiService(): RestInterface {
        return getRetrofit().create(RestInterface::class.java)
    }

}