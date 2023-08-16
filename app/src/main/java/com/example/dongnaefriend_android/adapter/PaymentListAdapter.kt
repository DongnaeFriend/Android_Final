package com.example.dongnaefriend_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dongnaefriend_android.R
import com.example.dongnaefriend_android.databinding.ItemDongnaeshareBinding
import com.example.dongnaefriend_android.databinding.ItemPaymentlistBinding
import model.PaymentListData
import model.Post

class PaymentListAdapter: RecyclerView.Adapter<PaymentListAdapter.ViewHolder>(){
    var dataList = mutableListOf<PaymentListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPaymentlistBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dataList[position])

    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(private val binding: ItemPaymentlistBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item : PaymentListData){
            binding.PLCategoryText.text = item.category
            binding.PLPaymentMemo.text = item.memo
            binding.PLMoneyAmount.text = item.moneyAmount.toString()
        }
    }


}