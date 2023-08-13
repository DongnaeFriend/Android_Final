package com.example.dongnaefriend_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.example.dongnaefriend_android.databinding.FragmentAccountbookBinding
import com.example.dongnaefriend_android.databinding.FragmentAccountbookDetailBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AccountbookDetailFragment : Fragment() {

    private lateinit var binding : FragmentAccountbookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val calendarView : CalendarView = binding.calendarAccountDetail
        val dataFormat : DateFormat = SimpleDateFormat("yyyy년mm월dd일")
        val date : Date = Date(calendarView.date)
        binding.calendarAccountDetail.setOnDateChangeListener{calendarView,year,month,dayOfMonth ->
            var day = "${year}년 ${month}월 ${dayOfMonth}일"
            binding.dayText.text = day
        }

        return binding.root
    }

//
}