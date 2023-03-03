package com.geektech.myapplicationapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geektech.myapplicationapi.databinding.RvItemsBinding

class RvAdapter: RecyclerView.Adapter<RvAdapter.ImageViewHolder>(){

    private val list= mutableListOf<ImageModel>()
    class ImageViewHolder(private val binding: RvItemsBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(image:ImageModel){

            binding.tvImage.load(image.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       val binding=RvItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()=list.size

    fun addList(list: List<ImageModel>){   //this.list.clear()
        this.list.addAll(0,list)
        notifyDataSetChanged()
    }
}
