package com.geektech.myapplicationapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    fun getImages(
        @Query("key")key:String="34088136-710a7f1f79d6b0120efa81592",
        @Query("q") q:String,
        @Query("page") page: Int=1,
        @Query("per_page") per_page:Int=20,
    ): Call<PixabayModel>
}