package com.example.dongnaefriend_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dongnaefriend_android.databinding.FragmentAccountshareWriteBinding
import com.example.dongnaefriend_android.databinding.FragmentDongnaeWriteBinding

class DongnaeWriteFragment : Fragment() {
    lateinit var binding: FragmentDongnaeWriteBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDongnaeWriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvComplete.setOnClickListener{

            val intent = Intent(getActivity(), AccountbookActivity::class.java)
            intent.putExtra("text",2)
            startActivity(intent)
        }
    }

}

