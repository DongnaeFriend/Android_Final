package com.example.dongnaefriend_android

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaefriend_android.databinding.FragmentMypageSettingBinding

class MypageSettingFragment : Fragment() {
    lateinit var binding: FragmentMypageSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageSettingBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageviewMypagesettingBack.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,MypageFragment()).commit()
        }


        binding.userInfo2.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,MypageBlockFragment()).commit()
        }
    }

}