package com.geektech.myapplicationapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView
import com.geektech.myapplicationapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private val adapter=RvAdapter()
    private var page=0
    private var  isScroll = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.rvAdapter?.adapter=adapter

        initListeners()

    }

    private fun initListeners() {
        binding?.etQuery?.setOnClickListener {
            page=0
            binding?.btnNextPage?.text="Search"
        }
        binding?.btnNextPage?.setOnClickListener {
            page++
            binding?.btnNextPage?.text="Next page"
            makeRequest(page)
        }
        binding?.rvAdapter?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                isScroll = true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isScroll && dy < 0){
                    page++
                    makeRequest(page)
                    isScroll = false
                }
            }

        })

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