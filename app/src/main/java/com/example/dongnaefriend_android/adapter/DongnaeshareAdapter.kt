package com.example.dongnaefriend_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaefriend_android.R
import com.example.dongnaefriend_android.databinding.ItemAccountshareBinding
import com.example.dongnaefriend_android.databinding.ItemDongnaeshareBinding
import model.Post


class DongnaeshareAdapter: RecyclerView.Adapter<DongnaeshareAdapter.ViewHolder>() {

    var dataList = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDongnaeshareBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dataList[position])

    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(private val binding: ItemDongnaeshareBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(dongnaeshare: Post){
            binding.ivItemDongnaeshareCover.setImageResource(dongnaeshare.cover)
            binding.tvItemDongnaeshareTitle.text = dongnaeshare.title
            binding.tvItemDongnaeshareMention.text = dongnaeshare.mention
            binding.tvItemDongnaeshareLike.text = dongnaeshare.like
            binding.tvItemDongnaeshareText.text = dongnaeshare.text
            binding.tvItemDongnaeshareLoc.text = dongnaeshare.loc
            binding.tvItemDongnaeshareTime.text = dongnaeshare.time
            binding.tvItemDongnaeshareNum.text = dongnaeshare.num
        }
    }
}
