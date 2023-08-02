package com.example.dongnaefriend_android

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.dongnaefriend_android.databinding.FragmentMyaccountBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import java.lang.Math.abs

class MyaccountFragment : Fragment() {

    private lateinit var binding: FragmentMyaccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyaccountBinding.inflate(layoutInflater)




        binding.tvMyacountDetail.setOnClickListener {
            val intent = Intent(getActivity(),MyAccountDetailActivity::class.java)
            startActivity(intent)
        }


        val expesion = 330000
        val setBudget = 300000
        val currentDay = 10
        val presentMonthDayCount = 30
        val leftBudget = setBudget - expesion
        val setBudgetPerDay = setBudget / presentMonthDayCount * currentDay

        var myAccountText1 : String = ""
        var myAccountText2 : String = ""



        //arrayof(item)을 현재 월까지 추가
        var currentYear = 2023
        var currentMonth = 7
        var spinnerPosition = -1
        var item : Array<String> = emptyArray()

        var setYear = 2021
        var setMonth = 0

        loop@for(i in 2022.. 2026){
            for(j in 1..12){
                item = item.plus("${i}년 ${j}월")
                spinnerPosition += 1
                if(i>=currentYear && j>=currentMonth){
                    break@loop
                }
            }
        }

        val a = (activity as AccountbookActivity)

        val myAdapterYear = ArrayAdapter(a, R.layout.simple_spinner_dropdown_item, item)

        binding.spinnerAccountCurrentMonth.adapter = myAdapterYear
        binding.spinnerAccountCurrentMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {


                if (position ==  spinnerPosition){
                    if (setBudget<expesion){
                        myAccountText2 = "이번 달 예산을 ${abs(setBudget-expesion)}만큼 초과했어요.\n절약이 절실해요"
                        binding.tvLeftbudget.text = myAccountText2
                    }
                    else{
                        if (setBudgetPerDay>=expesion){
                            myAccountText2 = "너무 잘하고 있어요!"
                        }
                        else{
                            myAccountText2 = "조금만 더 절약해봐요!"
                        }
                        binding.tvLeftbudget.text = "${myAccountText1} \n ${myAccountText2}"
                    }
                }
                else {
                    setYear = 2021
                    var positionCount = -1
                    loop@for(i in 2022.. 2026){
                        setYear += 1
                        setMonth = 0
                        for(j in 1..12){
                            setMonth += 1
                            positionCount += 1
                            if(positionCount == position){
                                break@loop
                            }
                        }
                    }
                    myAccountText1 = "${setYear}년 ${setMonth}월 목표 지출액은 ${setBudget}원이고, \n ${expesion}원을 지출했어요."
                    binding.tvLeftbudget.text = myAccountText1
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }
        binding.spinnerAccountCurrentMonth.setSelection(spinnerPosition)




        var expendFood = 1
        var expendTransport = 1
        var expendCulture = 1
        var expendDaily = 0
        var expendUsed = 0
        var expendFasion = 0
        var expendHealth = 0
        var expendEducation = 0
        var expendFixed = 0
        var expendOtt = 0




        //각 칵테고리의 지출이 0이면 안 보이게
        if(expendFood == 0){
            binding.LinearAccountLower1.visibility = View.GONE
        }

        if(expendTransport == 0){
            binding.LinearAccountLower2.visibility = View.GONE
        }

        if(expendCulture == 0){
            binding.LinearAccountLower3.visibility = View.GONE
        }

        if(expendDaily == 0){
            binding.LinearAccountLower4.visibility = View.GONE
        }

        if(expendUsed == 0){
            binding.LinearAccountLower5.visibility = View.GONE
        }

        if(expendFasion == 0){
            binding.LinearAccountLower6.visibility = View.GONE
        }

        if(expendHealth == 0){
            binding.LinearAccountLower7.visibility = View.GONE
        }

        if(expendEducation == 0){
            binding.LinearAccountLower8.visibility = View.GONE
        }

        if(expendFixed == 0){
            binding.LinearAccountLower9.visibility = View.GONE
        }

        if(expendOtt == 0){
            binding.LinearAccountLower10.visibility = View.GONE
        }




        //원형 차트 라이브러리 https://youngest-programming.tistory.com/273 참고
        binding.chart.setUsePercentValues(true)

        val valueUsed = 0f



// data Set
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(30f, "식비"))
        entries.add(PieEntry(40f, "교통비"))
        entries.add(PieEntry(40f, "문화"))
        if(valueUsed != 0f){
            entries.add(PieEntry(valueUsed,"중고거래"))}


        // add a lot of colors
        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for (c in COLORFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 16f
        }

        val pieData = PieData(pieDataSet)
        binding.chart.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            setEntryLabelColor(Color.BLACK)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }








        return binding.root
    }

}