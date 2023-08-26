package com.example.dongnaefriend_android

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dongnaefriend_android.Retrofit2.PostMoneyHistory
import com.example.dongnaefriend_android.Retrofit2.PostPeed
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.databinding.FragmentAccountshareWriteBinding
import com.example.dongnaefriend_android.databinding.FragmentDongnaeWriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DongnaeWriteFragment : Fragment() {
    lateinit var binding: FragmentDongnaeWriteBinding
    var category = 0

    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy = retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    //private val authToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
    private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDongnaeWriteBinding.inflate(inflater, container, false)

        binding.btnRestaurant.setOnClickListener{
            binding.btnRestaurant.setBackgroundColor(Color.parseColor("#ffc536"))
            binding.btnFacility.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnDisccount.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnTogether.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnQuestion.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnEtc.setBackgroundColor(Color.parseColor("#efeff0"))

            category = 0
        }

        binding.btnFacility.setOnClickListener{
            binding.btnRestaurant.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnFacility.setBackgroundColor(Color.parseColor("#ffc536"))
            binding.btnDisccount.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnTogether.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnQuestion.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnEtc.setBackgroundColor(Color.parseColor("#efeff0"))

            category = 1
        }

        binding.btnDisccount.setOnClickListener{
            binding.btnRestaurant.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnFacility.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnDisccount.setBackgroundColor(Color.parseColor("#ffc536"))
            binding.btnTogether.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnQuestion.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnEtc.setBackgroundColor(Color.parseColor("#efeff0"))

            category = 2
        }

        binding.btnTogether.setOnClickListener{
            binding.btnRestaurant.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnFacility.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnDisccount.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnTogether.setBackgroundColor(Color.parseColor("#ffc536"))
            binding.btnQuestion.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnEtc.setBackgroundColor(Color.parseColor("#efeff0"))

            category = 3
        }

        binding.btnQuestion.setOnClickListener{
            binding.btnRestaurant.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnFacility.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnDisccount.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnTogether.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnQuestion.setBackgroundColor(Color.parseColor("#ffc536"))
            binding.btnEtc.setBackgroundColor(Color.parseColor("#efeff0"))

            category = 4
        }


        binding.btnEtc.setOnClickListener{
            binding.btnRestaurant.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnFacility.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnDisccount.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnTogether.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnQuestion.setBackgroundColor(Color.parseColor("#efeff0"))
            binding.btnEtc.setBackgroundColor(Color.parseColor("#ffc536"))

            category = 5
        }

        binding.imgBack.setOnClickListener{
            val intent = Intent(getActivity(), DongnaeInformationActivity::class.java)
            startActivity(intent)
        }

        binding.tvComplete.setOnClickListener{
            val intent = Intent(getActivity(), DongnaeInformationActivity::class.java)
            startActivity(intent)

            var title = binding.tvTitle.text.toString()
            var content = binding.tvText.text.toString()

            val data = PostPeed(category,title,content,"올댓마인드","문래동")
            api.postPeed(data,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("글쓰기 포스트", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "글쓰기 포스트 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "글쓰기 연결 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

        }

        return binding.root
    }

}

