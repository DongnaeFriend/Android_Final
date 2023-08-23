package com.example.dongnaefriend_android

import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaefriend_android.databinding.FragmentHomeBinding
import com.example.dongnaefriend_android.databinding.FragmentMypageProfileBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.tvAccountdetail.setOnClickListener {
            val intent = Intent(getActivity(), AccountbookActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        binding.goAccountbook.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,AccountshareFragment()).commit()
        }
        binding.goDongnae.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,DongnaeRestaurantFragment()).commit()
        }

         */
    }


}