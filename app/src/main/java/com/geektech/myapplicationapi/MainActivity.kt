package com.geektech.myapplicationapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.geektech.myapplicationapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private val adapter=RvAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.rvAdapter?.adapter=adapter

        var page=0
        binding?.btnNextPage?.setOnClickListener {
            page++
            makeRequest(page)
        }
    }

    private fun makeRequest(page: Int) {
    App.api.getImages(q=binding?.etQuery?.text.toString(),page=page, per_page=4)
        .enqueue(
            object: Callback<PixabayModel>{
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    adapter.addList(response.body()!!.hits)
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("TAG","onFailure ${t.stackTrace}")
                }
            }
        )
    }
}