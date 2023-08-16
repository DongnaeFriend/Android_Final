package com.example.dongnaefriend_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.adapter.AccountshareAdapter
import com.example.dongnaefriend_android.adapter.PaymentListAdapter
import com.example.dongnaefriend_android.databinding.FragmentAccountbookBinding
import com.example.dongnaefriend_android.databinding.FragmentAccountbookDetailBinding
import model.PaymentListData
import model.Post
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AccountbookDetailFragment : Fragment() {

    private lateinit var binding : FragmentAccountbookDetailBinding
    private lateinit var paymentListAdapter : PaymentListAdapter
    private val PaymentListData = mutableListOf<PaymentListData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentAccountbookDetailBinding.inflate(layoutInflater)

        var now : Long = System.currentTimeMillis()
        var nowdate : Date = Date(now)
        val dataFormat : DateFormat = SimpleDateFormat("yyyy-MM-dd")

        val getTime = dataFormat.format(nowdate)
        binding.dayText.text = getTime


        val calendarView = binding.calendarAccountDetail
        val date : Date = Date(calendarView.date)
        binding.calendarAccountDetail.setOnDateChangeListener{calendarView,year,month,dayOfMonth ->
            var day = "${year}년 ${month+1}월 ${dayOfMonth}일"
            binding.dayText.text = day
        }
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

        binding.btnMemoPlus
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