package com.example.dongnaefriend_android

import android.app.backup.BackupAgent
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dongnaefriend_android.databinding.FragmentMypageBinding
import com.example.dongnaefriend_android.databinding.FragmentMypageProfileBinding
import com.kakao.sdk.user.UserApiClient


class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패 $error")
            } else if (user != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 성공 : $user")
                binding.textviewName.text = user.kakaoAccount?.profile?.nickname
                binding.textviewYears.text = user.kakaoAccount?.ageRange.toString()
                binding.tvComplete.text = user.kakaoAccount?.email
            }
        }




        binding.btnMypagebanner.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,MypageProfileFragment()).commit()
        }

    }
}