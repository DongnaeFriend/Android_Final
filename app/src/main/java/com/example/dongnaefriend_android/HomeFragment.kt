package com.example.dongnaefriend_android

import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaefriend_android.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var Binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentHomeBinding.inflate(layoutInflater)

        Binding.tvAccountdetail.setOnClickListener {
            val intent = Intent(context, AccountbookActivity::class.java)
            startActivity(intent)
        }



        return Binding.root
    }


}