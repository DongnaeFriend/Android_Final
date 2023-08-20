package com.example.dongnaefriend_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.Retrofit2.BudgetResponse
import com.example.dongnaefriend_android.Retrofit2.MoneyHistoryResponse
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.adapter.AccountshareAdapter
import com.example.dongnaefriend_android.adapter.PaymentListAdapter
import com.example.dongnaefriend_android.databinding.FragmentAccountbookBinding
import com.example.dongnaefriend_android.databinding.FragmentAccountbookDetailBinding
import model.PaymentListData
import model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AccountbookDetailFragment : Fragment() {

    private lateinit var binding : FragmentAccountbookDetailBinding
    private lateinit var paymentListAdapter : PaymentListAdapter
    private val PaymentListData = mutableListOf<PaymentListData>()
    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy = retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    private val authToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentAccountbookDetailBinding.inflate(layoutInflater)

        var now : Long = System.currentTimeMillis()
        var nowdate : Date = Date(now)
        val dataFormat : DateFormat = SimpleDateFormat("yyyy년 M월 dd일")

        val getTime = dataFormat.format(nowdate)
        binding.dayText.text = getTime


        val calendarView = binding.calendarAccountDetail
        val date : Date = Date(calendarView.date)
        binding.calendarAccountDetail.setOnDateChangeListener{calendarView,year,month,dayOfMonth ->
            var day = "${year}년 ${month+1}월 ${dayOfMonth}일"
            binding.dayText.text = day
        }


        var money = 123



        Runnable {
            api.getMoneyHistory(2023, 8, 20, "Bearer $authToken")
                .enqueue(object : Callback<MoneyHistoryResponse> {
                    // 전송 실패
                    override fun onFailure(call: Call<MoneyHistoryResponse>, t: Throwable) {
                        Log.d("지출수입내역get실패", t.message!!)
                    }

                    // 전송 성공
                    override fun onResponse(
                        call: Call<MoneyHistoryResponse>,
                        response: Response<MoneyHistoryResponse>
                    ) {
                        var array  = response.body()

                        Log.d("지출수입내역get성공", "response : ${response.body()?.transactions}") // 정상출력





                        //binding.tvWon.text = List<>

                        // 전송은 성공 but 서버 4xx 에러
                        Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                        Log.d("태그: 메시지", "response : ${response.message()}")
                        Log.d("태그: 코드", "response : ${response.code()}")
                    }

                })
        }.run()

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPaymentList()
        initPaymentListRecyclerView()

        binding.btnBackButton.setOnClickListener{
            val intent = Intent(getActivity(), AccountbookActivity::class.java)
            startActivity(intent)
        }







    }






    private fun initPaymentListRecyclerView() {
        binding.recyclerPaymentList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        paymentListAdapter = PaymentListAdapter()
        paymentListAdapter.dataList = PaymentListData
        binding.recyclerPaymentList.adapter = paymentListAdapter
    }

    private fun initPaymentList() {
        PaymentListData.addAll(
            listOf<PaymentListData>(
                PaymentListData(
                    "식비", "제육", 8000
                ),
                PaymentListData(
                    "식비", "아메리카노", 5000
                ),
                PaymentListData(
                    "생필품", "두루마리 휴지", 3000
                ),


            )
        )
    }






}