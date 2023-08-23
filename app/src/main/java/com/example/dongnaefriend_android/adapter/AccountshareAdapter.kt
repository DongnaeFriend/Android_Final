package com.example.dongnaefriend_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaefriend_android.databinding.ItemAccountshareBinding
import model.AccountsharePost

class AccountshareAdapter: RecyclerView.Adapter<AccountshareAdapter.ViewHolder>() {

    var dataList = mutableListOf<AccountsharePost>()
    var onItemClick: ((AccountsharePost) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccountshareBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val accountshare = dataList[position]
        holder.onBind(accountshare)
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(private val binding: ItemAccountshareBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = dataList[position]
                    onItemClick?.invoke(item)
                }
            }
        }

        fun onBind(accountshare: AccountsharePost){
            binding.ivItemAccountshareCover.setImageResource(accountshare.cover)
            binding.tvItemAccountshareTitle.text = accountshare.title
            binding.tvItemAccountshareMention.text = accountshare.mention
            binding.tvItemAccountshareLike.text = accountshare.like.toString()
            binding.tvItemAccountshareText.text = accountshare.text
            binding.tvItemAccountshareTime.text = accountshare.time
            binding.tvItemAccountshareNum.text = accountshare.num.toString()
            binding.ivItemAccountshareCategory.setImageResource(accountshare.categooryimg)  // Ensure this property name is correct
            binding.tvItemAccountshareCategory.text = accountshare.categorytext
        }
    }
}
