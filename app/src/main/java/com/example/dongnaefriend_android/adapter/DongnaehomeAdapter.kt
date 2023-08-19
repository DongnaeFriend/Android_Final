package com.example.dongnaefriend_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaefriend_android.databinding.ItemDongnaehomeBinding
import model.DongnaeHomeItem

class DongnaehomeAdapter : RecyclerView.Adapter<DongnaehomeAdapter.ViewHolder>() {

    var dataList = mutableListOf<DongnaeHomeItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDongnaehomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(private val binding: ItemDongnaehomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(dongnaeHomeItem: DongnaeHomeItem) {
            binding.imageView.setImageResource(dongnaeHomeItem.cover)
            binding.restaurant_category_title.text = dongnaeHomeItem.title
            binding.dateTextView.text = dongnaeHomeItem.date
            binding.mentionTextView.text = dongnaeHomeItem.mention
            binding.likeTextView.text = dongnaeHomeItem.like
            binding.textTextView.text = dongnaeHomeItem.text
            binding.locTextView.text = dongnaeHomeItem.loc
            binding.timeTextView.text = dongnaeHomeItem.time
            binding.numTextView.text = dongnaeHomeItem.num
        }
    }}

