package com.example.dongnaefriend_android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaefriend_android.databinding.FragmentAccountbookBinding


class AccountbookFragment : Fragment() {
    private lateinit var Binding:FragmentAccountbookBinding

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentAccountbookBinding.inflate(layoutInflater)
        return Binding.root


    }

    //fragment가 시작되자마자 액티비티로 이동(fragment에서는 다른 fragment로의 이동이 어려움)
    override fun onStart() {
        super.onStart()
        val intent = Intent(getActivity(), AccountbookActivity::class.java)
        startActivity(intent)
    }


}