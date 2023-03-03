package com.geektech.myapplicationapi

import android.app.Application

class App: Application() {

    companion object {
        lateinit var api:PixabayApi
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit=RetrofitService()
        api=retrofit.getApi()
    }
}