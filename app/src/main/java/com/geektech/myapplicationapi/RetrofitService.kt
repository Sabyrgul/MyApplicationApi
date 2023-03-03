package com.geektech.myapplicationapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val retrofit=Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun getApi()=retrofit.create(PixabayApi::class.java)

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getInterceptor())
            .build()
    }

    private fun getInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}