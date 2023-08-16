package com.example.dongnaefriend_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaefriend_android.AccountshareFragment
import com.example.dongnaefriend_android.databinding.ItemAccountshareBinding
import model.AccountsharePost
import model.Post

class AccountshareAdapter: RecyclerView.Adapter<AccountshareAdapter.ViewHolder>() {

    var dataList = mutableListOf<AccountsharePost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccountshareBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(private val binding: ItemAccountshareBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(accountshare: AccountsharePost){
            binding.ivItemAccountshareCover.setImageResource(accountshare.cover)
            binding.tvItemAccountshareTitle.text = accountshare.title
            binding.tvItemAccountshareMention.text = accountshare.mention
            binding.tvItemAccountshareLike.text = accountshare.like
            binding.tvItemAccountshareText.text = accountshare.text
            binding.tvItemAccountshareTime.text = accountshare.time
            binding.tvItemAccountshareNum.text = accountshare.num
            binding.ivItemAccountshareCategory.setImageResource(accountshare.categooryimg)
            binding.tvItemAccountshareCategory.text = accountshare.categorytext
        }
    }
}
