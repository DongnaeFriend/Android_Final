package com.example.dongnaefriend_android

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.app.NotificationCompat.getColor
import com.example.dongnaefriend_android.Retrofit2.AccountAllResponse
import com.example.dongnaefriend_android.Retrofit2.BudgetResponse
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.databinding.FragmentMyaccountBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Math.abs
import java.text.DecimalFormat
import java.util.Calendar



class MyaccountFragment : Fragment() {

    private lateinit var binding: FragmentMyaccountBinding
    var setBudget = 0
    var expenditure : Int = 0

    var percentage0 = 0
    var percentage1 = 0
    var percentage2 = 0
    var percentage3 = 0
    var percentage4 = 0
    var percentage5 = 0
    var percentage6 = 0
    var percentage7 = 0
    var percentage8 = 0
    var percentage9 = 0
    var percentage10 = 0
    var percentage11 = 0
    var percentage12 = 0
    var percentage13 = 0
    var percentage14 = 0
    val decimal = DecimalFormat("#,###")









    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy = retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    //private val authToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
    private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyaccountBinding.inflate(layoutInflater)




        val currentDay = 10
        val presentMonthDayCount = 30
        val leftBudget = setBudget - expenditure
        val setBudgetPerDay = setBudget / presentMonthDayCount * currentDay

        var myAccountText1 : String = ""
        var myAccountText2 : String = ""



        //arrayof(item)을 현재 월까지 추가
        var currentYear = 2023
        var currentMonth = 8
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

                    var check = true

                    api.getBudget(2023, 7,"Bearer $authToken").enqueue(object : Callback<BudgetResponse> {
                        // 전송 실패
                        override fun onFailure(call: Call<BudgetResponse>, t: Throwable) {
                            Log.d("************", t.message!!)
                        }
                        // 전송 성공
                        override fun onResponse(call: Call<BudgetResponse>, response: Response<BudgetResponse>) {
                            Log.d("예산 get", "response : ${response.body()?.budget}") // 정상출력


                            if (response.body()?.budget == null){
                                binding.tvLeftbudget.text = "이번달 예산을 입력해주세요!"
                                check = false

                            }
                            else {
                                setBudget = response.body()?.budget!!
                            }

                            // 전송은 성공 but 서버 4xx 에러
                            Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                            Log.d("태그: 메시지", "response : ${response.message()}")
                            Log.d("태그: 코드", "response : ${response.code()}")
                        }

                    })

                    api.getAccountAll(2023, 7,"Bearer $authToken").enqueue(object : Callback<AccountAllResponse> {
                        // 전송 실패
                        override fun onFailure(call: Call<AccountAllResponse>, t: Throwable) {
                            Log.d("AccountAll실패", t.message!!)
                        }
                        // 전송 성공
                        @SuppressLint("ResourceAsColor")
                        override fun onResponse(call: Call<AccountAllResponse>, response: Response<AccountAllResponse>) {


                            Log.d("AccountAll성공", "response : expenditure : ${response.body()?.expenditure}")
                            Log.d("AccountAll성공", "response : income : ${response.body()?.income}") // 정상출력
                            Log.d("AccountAll성공", "response : Budget :  ${response.body()?.budget}")
                            Log.d("AccountAll성공", "response : expense : ${response.body()?.expense}")

                            val decimal = DecimalFormat("#,###")

                            //binding.textviewPaymentamount.text = "${decimal.format(response.body()?.expenditure.toString())}원"
                            //binding.tvIncomeAmount.text = "${decimal.format(response.body()?.income.toString())}원"
                            //binding.textviewPaymentamount.text = "${decimal.format(response.body()?.expenditure)}원"
                            binding.textviewPaymentamount.text = "${response.body()?.expenditure}원"


                            //binding.tvIncomeAmount.text = "${decimal.format(response.body()?.income.toString())}원"
                            binding.tvIncomeAmount.text = "${response.body()?.income.toString()}원"


                            /*
                            if (response.body()?.income != null) {
                                var Income = response.body()?.income!!
                                binding.tvIncomeAmount.text = "${decimal.format(Income)}원"
                            }
                            else {
                                var Income = 0
                                binding.tvIncomeAmount.text = "${Income}원"
                            }

                             */


                            if (response.body()?.expenditure == null){
                                binding.tvLeftbudget.text = "이번달 예산은 ${setBudget}원이고, \n 지출 내역은 없습니다!"
                                check = false

                            }
                            else {
                                expenditure = response.body()?.expenditure!!
                            }

                            if(check == true) {
                                if (setBudget < expenditure) {
                                    myAccountText2 =
                                        "이번 달 예산을 ${abs(setBudget - expenditure)}원 만큼 초과했어요.\n절약이 절실해요\uD83D\uDCB8"
                                    binding.tvLeftbudget.text = myAccountText2

                                } else {

                                    //현재 날짜 불러오기
                                    var calendar = Calendar.getInstance()
                                    var day = calendar.get(Calendar.DAY_OF_MONTH)


                                    var budgetUntilToday = setBudget / 30 * day
                                    if (budgetUntilToday >= expenditure) {
                                        myAccountText2 = "너무 잘하고 있어요!\uD83E\uDD73"
                                    } else {
                                        myAccountText2 = "조금만 더 절약해봐요!\uD83D\uDE48"
                                    }
                                    myAccountText1 = "이번달 남은 예산은 ${setBudget - expenditure}원이에요"
                                    binding.tvLeftbudget.text = "${myAccountText1} \n ${myAccountText2}"
                                }
                            }
                            else{check == true}

                            var dataa = response.body()?.expense!!

                            val size = dataa.size

                            var dataDummy = arrayListOf<String>()


                            if (size>=1) {
                                var Data0 = dataa[0].toString()
                                var Split0 = Data0.split("List(",", ",")")

                                dataDummy.add(Split0[1])
                                dataDummy.add(Split0[2])
                            }

                            if (size>=2) {
                                var Data1 = dataa[1].toString()
                                var Split1 = Data1.split("List(",", ",")")

                                dataDummy.add(Split1[1])
                                dataDummy.add(Split1[2])
                            }

                            if (size>=3) {
                                var Data2 = dataa[2].toString()
                                var Split2 = Data2.split("List(",", ",")")

                                dataDummy.add(Split2[1])
                                dataDummy.add(Split2[2])
                            }

                            if (size>=4) {
                                var Data3 = dataa[3].toString()
                                var Split3 = Data3.split("List(",", ",")")

                                dataDummy.add(Split3[1])
                                dataDummy.add(Split3[2])
                            }

                            if (size>=5) {
                                var Data4 = dataa[4].toString()
                                var Split4 = Data4.split("List(",", ",")")

                                dataDummy.add(Split4[1])
                                dataDummy.add(Split4[2])
                            }

                            if (size>=6) {
                                var Data5 = dataa[5].toString()
                                var Split5 = Data5.split("List(",", ",")")

                                dataDummy.add(Split5[1])
                                dataDummy.add(Split5[2])
                            }

                            if (size>=7) {
                                var Data6 = dataa[6].toString()
                                var Split6 = Data6.split("List(",", ",")")

                                dataDummy.add(Split6[1])
                                dataDummy.add(Split6[2])
                            }

                            if (size>=8) {
                                var Data7 = dataa[7].toString()
                                var Split7 = Data7.split("List(",", ",")")

                                dataDummy.add(Split7[1])
                                dataDummy.add(Split7[2])
                            }

                            if (size>=9) {
                                var Data8 = dataa[8].toString()
                                var Split8 = Data8.split("List(",", ",")")

                                dataDummy.add(Split8[1])
                                dataDummy.add(Split8[2])
                            }

                            if (size>=10) {
                                var Data9 = dataa[9].toString()
                                var Split9 = Data9.split("List(",", ",")")

                                dataDummy.add(Split9[1])
                                dataDummy.add(Split9[2])
                            }

                            if (size>=11) {
                                var Data10 = dataa[10].toString()
                                var Split10 = Data10.split("List(",", ",")")

                                dataDummy.add(Split10[1])
                                dataDummy.add(Split10[2])
                            }

                            if (size>=12) {
                                var Data11 = dataa[11].toString()
                                var Split11 = Data11.split("List(",", ",")")

                                dataDummy.add(Split11[1])
                                dataDummy.add(Split11[2])
                            }

                            if (size>=13) {
                                var Data12 = dataa[12].toString()
                                var Split12 = Data12.split("List(",", ",")")

                                dataDummy.add(Split12[1])
                                dataDummy.add(Split12[2])
                            }

                            if (size>=14) {
                                var Data13 = dataa[13].toString()
                                var Split13 = Data13.split("List(",", ",")")

                                dataDummy.add(Split13[1])
                                dataDummy.add(Split13[2])
                            }

                            if (size>=15) {
                                var Data14 = dataa[14].toString()
                                var Split14 = Data14.split("List(",", ",")")

                                dataDummy.add(Split14[1])
                                dataDummy.add(Split14[2])
                            }




                            binding.LinearAccountLower1.visibility = View.GONE
                            binding.LinearAccountLower2.visibility = View.GONE
                            binding.LinearAccountLower3.visibility = View.GONE
                            binding.LinearAccountLower4.visibility = View.GONE
                            binding.LinearAccountLower5.visibility = View.GONE
                            binding.LinearAccountLower6.visibility = View.GONE
                            binding.LinearAccountLower7.visibility = View.GONE
                            binding.LinearAccountLower8.visibility = View.GONE
                            binding.LinearAccountLower9.visibility = View.GONE
                            binding.LinearAccountLower10.visibility = View.GONE
                            binding.LinearAccountLower11.visibility = View.GONE
                            binding.LinearAccountLower12.visibility = View.GONE
                            binding.LinearAccountLower13.visibility = View.GONE
                            binding.LinearAccountLower14.visibility = View.GONE
                            binding.LinearAccountLower15.visibility = View.GONE

                            percentage0 = 0
                            percentage1 = 0
                            percentage2 = 0
                            percentage3 = 0
                            percentage4 = 0
                            percentage5 = 0
                            percentage6 = 0
                            percentage7 = 0
                            percentage8 = 0
                            percentage9 = 0
                            percentage10 = 0
                            percentage11 = 0
                            percentage12 = 0
                            percentage13 = 0
                            percentage14 = 0





                            if(dataDummy.contains("transactionCategory=식비")){
                                binding.LinearAccountLower1.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=식비")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage0 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentFood.text = "${percentage0.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountFood.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountFood.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=카페/간식")){
                                binding.LinearAccountLower2.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=카페/간식")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage1 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentSnack.text = "${percentage1.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountSnack.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountSnack.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=편의점/마트")){
                                binding.LinearAccountLower3.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=편의점/마트")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage2 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentMart.text = "${percentage2.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountMart.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountMart.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=술/유흥")){
                                binding.LinearAccountLower4.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=술/유흥")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage3 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentAlchol.text = "${percentage3.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountAlchol.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountAlchol.text = "${Price}원"}
                            }
                            if(dataDummy.contains("transactionCategory=쇼핑")){
                                binding.LinearAccountLower5.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=쇼핑")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage4 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentShopping.text = "${percentage4.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountShopping.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountShopping.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=취미/여가")){
                                binding.LinearAccountLower6.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=취미/여가")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage5 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentHobby.text = "${percentage5.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountHobby.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountHobby.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=건강")){
                                binding.LinearAccountLower7.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=건강")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage6 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentHealth.text = "${percentage6.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountHealth.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountHealth.text = "${Price}원"}
                            }
                            if(dataDummy.contains("transactionCategory=주거/통신")){
                                binding.LinearAccountLower8.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=주거/통신")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage7 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentLiving.text = "${percentage7.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountLiving.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountLiving.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=보험/금융")){
                                binding.LinearAccountLower9.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=보험/금융")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage8 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentFund.text = "${percentage8.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountFund.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountFund.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=미용")){
                                binding.LinearAccountLower10.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=미용")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage9 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentBeauty.text = "${percentage9.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountBeauty.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountBeauty.text = "${Price}원"}
                            }
                            if(dataDummy.contains("transactionCategory=교통비")){
                                binding.LinearAccountLower11.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=교통비")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage10 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentTransport.text = "${percentage10.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountTransport.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountTransport.text = "${Price}원"}
                            }
                            if(dataDummy.contains("transactionCategory=여행/숙박")){
                                binding.LinearAccountLower12.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=여행/숙박")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage11 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentTrip.text = "${percentage11.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountTrip.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountTrip.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=교육")){
                                binding.LinearAccountLower13.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=교육")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage12 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentEducation.text = "${percentage12.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountEducation.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountEducation.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=저축/투자")){
                                binding.LinearAccountLower14.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=저축/투자")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage13 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentBank.text = "${percentage13.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountBank.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountBank.text = "${Price}원"}
                            }

                            if(dataDummy.contains("transactionCategory=기타(지출)")){
                                binding.LinearAccountLower15.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=기타(지출)")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage14 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentEtc.text = "${percentage14.toString()}%"
                                if(Price>=1000) {
                                    binding.tvAmountEtc.text = "${decimal.format(Price)}원"
                                }
                                else{binding.tvAmountEtc.text = "${Price}원"}
                            }



                            //원형 차트 라이브러리 https://youngest-programming.tistory.com/273 참고
                            binding.chart.setUsePercentValues(true)







// data Set
                            val entries = ArrayList<PieEntry>()
                            if(percentage0.toFloat() != 0f){
                                entries.add(PieEntry(percentage0.toFloat(),"식비"))
                            }
                            if(percentage1.toFloat() != 0f){
                                entries.add(PieEntry(percentage1.toFloat(),"카페/간식"))
                            }
                            if(percentage2.toFloat() != 0f){
                                entries.add(PieEntry(percentage2.toFloat(),"편의점/마트"))
                            }
                            if(percentage3.toFloat() != 0f){
                                entries.add(PieEntry(percentage3.toFloat(),"술/유흥"))
                            }
                            if(percentage4.toFloat() != 0f){
                                entries.add(PieEntry(percentage4.toFloat(),"쇼핑"))
                            }
                            if(percentage5.toFloat() != 0f){
                                entries.add(PieEntry(percentage5.toFloat(),"취미/여가"))
                            }
                            if(percentage6.toFloat() != 0f){
                                entries.add(PieEntry(percentage6.toFloat(),"건강"))
                            }
                            if(percentage7.toFloat() != 0f){
                                entries.add(PieEntry(percentage7.toFloat(),"주거/통신"))
                            }
                            if(percentage8.toFloat() != 0f){
                                entries.add(PieEntry(percentage8.toFloat(),"보험/금융"))
                            }
                            if(percentage9.toFloat() != 0f){
                                entries.add(PieEntry(percentage9.toFloat(),"미용"))
                            }
                            if(percentage10.toFloat() != 0f){
                                entries.add(PieEntry(percentage10.toFloat(),"교통비"))
                            }
                            if(percentage11.toFloat() != 0f){
                                entries.add(PieEntry(percentage11.toFloat(),"여행/숙박"))
                            }
                            if(percentage12.toFloat() != 0f){
                                entries.add(PieEntry(percentage12.toFloat(),"교육"))
                            }
                            if(percentage13.toFloat() != 0f){
                                entries.add(PieEntry(percentage13.toFloat(),"저축/투자"))
                            }
                            if(percentage14.toFloat() != 0f){
                                entries.add(PieEntry(percentage14.toFloat(),"기타(지출)"))
                            }

                            Log.d("퍼센테이지","${percentage0}")



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
















                            // 전송은 성공 but 서버 4xx 에러
                            Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                            Log.d("태그: 메시지", "response : ${response.message()}")
                            Log.d("태그: 코드", "response : ${response.code()}")
                        }

                    })


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
                    var check = true


                    api.getBudget(setYear, setMonth,"Bearer $authToken").enqueue(object : Callback<BudgetResponse> {
                        // 전송 실패
                        override fun onFailure(call: Call<BudgetResponse>, t: Throwable) {
                            Log.d("************", t.message!!)
                        }
                        // 전송 성공
                        override fun onResponse(call: Call<BudgetResponse>, response: Response<BudgetResponse>) {
                            Log.d("예산 get", "response : ${response.body()?.budget}") // 정상출력


                            if (response.body()?.budget == null){
                                binding.tvLeftbudget.text = "해당하는 달의 정보가 없습니다"
                                check = false

                            }
                            else {
                                setBudget = response.body()?.budget!!
                            }

                            // 전송은 성공 but 서버 4xx 에러
                            Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                            Log.d("태그: 메시지", "response : ${response.message()}")
                            Log.d("태그: 코드", "response : ${response.code()}")
                        }

                    })

                    api.getAccountAll(setYear, setMonth,"Bearer $authToken").enqueue(object : Callback<AccountAllResponse> {
                        // 전송 실패
                        override fun onFailure(call: Call<AccountAllResponse>, t: Throwable) {
                            Log.d("AccountAll실패", t.message!!)
                        }
                        // 전송 성공
                        override fun onResponse(call: Call<AccountAllResponse>, response: Response<AccountAllResponse>) {


                            Log.d("AccountAll성공", "response : expenditure : ${response.body()?.expenditure}")
                            Log.d("AccountAll성공", "response : income : ${response.body()?.income}") // 정상출력
                            Log.d("AccountAll성공", "response : Budget :  ${response.body()?.budget}")
                            Log.d("AccountAll성공", "response : expense : ${response.body()?.expense}")

                            binding.textviewPaymentamount.text = response.body()?.expenditure.toString()
                            binding.tvIncomeAmount.text = response.body()?.income.toString()

                            if (response.body()?.expenditure == null){
                                binding.tvLeftbudget.text = "해당하는 달의 정보가 없습니다"
                                check = false

                            }
                            else {
                                expenditure = response.body()?.expenditure!!
                            }

                            if (check == true) {
                                myAccountText1 =
                                    "${setYear}년 ${setMonth}월 목표 지출액은 ${setBudget}원이고, \n ${expenditure}원을 지출했어요."
                                binding.tvLeftbudget.text = myAccountText1
                            }
                            else if(check == false){
                                check == true
                            }


                            var dataa = response.body()?.expense!!

                            val size = dataa.size

                            var dataDummy = arrayListOf<String>()


                            if (size>=1) {
                                var Data0 = dataa[0].toString()
                                var Split0 = Data0.split("List(",", ",")")

                                dataDummy.add(Split0[1])
                                dataDummy.add(Split0[2])
                            }

                            if (size>=2) {
                                var Data1 = dataa[1].toString()
                                var Split1 = Data1.split("List(",", ",")")

                                dataDummy.add(Split1[1])
                                dataDummy.add(Split1[2])
                            }

                            if (size>=3) {
                                var Data2 = dataa[2].toString()
                                var Split2 = Data2.split("List(",", ",")")

                                dataDummy.add(Split2[1])
                                dataDummy.add(Split2[2])
                            }

                            if (size>=4) {
                                var Data3 = dataa[3].toString()
                                var Split3 = Data3.split("List(",", ",")")

                                dataDummy.add(Split3[1])
                                dataDummy.add(Split3[2])
                            }

                            if (size>=5) {
                                var Data4 = dataa[4].toString()
                                var Split4 = Data4.split("List(",", ",")")

                                dataDummy.add(Split4[1])
                                dataDummy.add(Split4[2])
                            }

                            if (size>=6) {
                                var Data5 = dataa[5].toString()
                                var Split5 = Data5.split("List(",", ",")")

                                dataDummy.add(Split5[1])
                                dataDummy.add(Split5[2])
                            }

                            if (size>=7) {
                                var Data6 = dataa[6].toString()
                                var Split6 = Data6.split("List(",", ",")")

                                dataDummy.add(Split6[1])
                                dataDummy.add(Split6[2])
                            }

                            if (size>=8) {
                                var Data7 = dataa[7].toString()
                                var Split7 = Data7.split("List(",", ",")")

                                dataDummy.add(Split7[1])
                                dataDummy.add(Split7[2])
                            }

                            if (size>=9) {
                                var Data8 = dataa[8].toString()
                                var Split8 = Data8.split("List(",", ",")")

                                dataDummy.add(Split8[1])
                                dataDummy.add(Split8[2])
                            }

                            if (size>=10) {
                                var Data9 = dataa[9].toString()
                                var Split9 = Data9.split("List(",", ",")")

                                dataDummy.add(Split9[1])
                                dataDummy.add(Split9[2])
                            }

                            if (size>=11) {
                                var Data10 = dataa[10].toString()
                                var Split10 = Data10.split("List(",", ",")")

                                dataDummy.add(Split10[1])
                                dataDummy.add(Split10[2])
                            }

                            if (size>=12) {
                                var Data11 = dataa[11].toString()
                                var Split11 = Data11.split("List(",", ",")")

                                dataDummy.add(Split11[1])
                                dataDummy.add(Split11[2])
                            }

                            if (size>=13) {
                                var Data12 = dataa[12].toString()
                                var Split12 = Data12.split("List(",", ",")")

                                dataDummy.add(Split12[1])
                                dataDummy.add(Split12[2])
                            }

                            if (size>=14) {
                                var Data13 = dataa[13].toString()
                                var Split13 = Data13.split("List(",", ",")")

                                dataDummy.add(Split13[1])
                                dataDummy.add(Split13[2])
                            }

                            if (size>=15) {
                                var Data14 = dataa[14].toString()
                                var Split14 = Data14.split("List(",", ",")")

                                dataDummy.add(Split14[1])
                                dataDummy.add(Split14[2])
                            }




                            binding.LinearAccountLower1.visibility = View.GONE
                            binding.LinearAccountLower2.visibility = View.GONE
                            binding.LinearAccountLower3.visibility = View.GONE
                            binding.LinearAccountLower4.visibility = View.GONE
                            binding.LinearAccountLower5.visibility = View.GONE
                            binding.LinearAccountLower6.visibility = View.GONE
                            binding.LinearAccountLower7.visibility = View.GONE
                            binding.LinearAccountLower8.visibility = View.GONE
                            binding.LinearAccountLower9.visibility = View.GONE
                            binding.LinearAccountLower10.visibility = View.GONE
                            binding.LinearAccountLower11.visibility = View.GONE
                            binding.LinearAccountLower12.visibility = View.GONE
                            binding.LinearAccountLower13.visibility = View.GONE
                            binding.LinearAccountLower14.visibility = View.GONE
                            binding.LinearAccountLower15.visibility = View.GONE

                            percentage0 = 0
                            percentage1 = 0
                            percentage2 = 0
                            percentage3 = 0
                            percentage4 = 0
                            percentage5 = 0
                            percentage6 = 0
                            percentage7 = 0
                            percentage8 = 0
                            percentage9 = 0
                            percentage10 = 0
                            percentage11 = 0
                            percentage12 = 0
                            percentage13 = 0
                            percentage14 = 0





                            if(dataDummy.contains("transactionCategory=식비")){
                                binding.LinearAccountLower1.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=식비")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage0 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentFood.text = percentage0.toString()
                                binding.tvAmountFood.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=카페/간식")){
                                binding.LinearAccountLower2.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=카페/간식")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage1 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentSnack.text = percentage1.toString()
                                binding.tvAmountSnack.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=편의점/마트")){
                                binding.LinearAccountLower3.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=편의점/마트")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage2 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentMart.text = percentage2.toString()
                                binding.tvAmountMart.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=술/유흥")){
                                binding.LinearAccountLower4.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=술/유흥")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage3 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentAlchol.text = percentage3.toString()
                                binding.tvAmountAlchol.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=쇼핑")){
                                binding.LinearAccountLower5.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=쇼핑")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage4 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentShopping.text = percentage4.toString()
                                binding.tvAmountShopping.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=취미/여가")){
                                binding.LinearAccountLower6.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=취미/여가")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage5 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentHobby.text = percentage5.toString()
                                binding.tvAmountHobby.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=건강")){
                                binding.LinearAccountLower7.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=건강")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage6 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentHealth.text = percentage6.toString()
                                binding.tvAmountHealth.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=주거/통신")){
                                binding.LinearAccountLower8.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=주거/통신")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage7 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentLiving.text = percentage7.toString()
                                binding.tvAmountLiving.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=보험/금융")){
                                binding.LinearAccountLower9.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=보험/금융")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage8 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentFund.text = percentage8.toString()
                                binding.tvAmountFund.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=미용")){
                                binding.LinearAccountLower10.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=미용")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage9 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentBeauty.text = percentage9.toString()
                                binding.tvAmountBeauty.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=교통비")){
                                binding.LinearAccountLower11.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=교통비")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage10 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentTransport.text = percentage10.toString()
                                binding.tvAmountTransport.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=여행/숙박")){
                                binding.LinearAccountLower12.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=여행/숙박")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage11 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentTrip.text = percentage11.toString()
                                binding.tvAmountTrip.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=교육")){
                                binding.LinearAccountLower13.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=교육")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage12 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentEducation.text = percentage12.toString()
                                binding.tvAmountEducation.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=저축/투자")){
                                binding.LinearAccountLower14.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=저축/투자")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage13 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentBank.text = percentage13.toString()
                                binding.tvAmountBank.text = Price.toString()
                            }

                            if(dataDummy.contains("transactionCategory=기타(지출)")){
                                binding.LinearAccountLower15.visibility = View.VISIBLE

                                var index = dataDummy.indexOf("transactionCategory=기타(지출)")
                                var price = dataDummy[index+1]
                                var Split = price.split("=")
                                var Price = Split[1].toInt()
                                percentage14 = (Price*100/response.body()?.expenditure!!)
                                binding.tvPercentEtc.text = percentage14.toString()
                                binding.tvAmountEtc.text = Price.toString()
                            }

                            //원형 차트 라이브러리 https://youngest-programming.tistory.com/273 참고
                            binding.chart.setUsePercentValues(true)





// data Set
                            val entries = ArrayList<PieEntry>()
                            if(percentage0.toFloat() != 0f){
                                entries.add(PieEntry(percentage0.toFloat(),"식비"))
                            }
                            if(percentage1.toFloat() != 0f){
                                entries.add(PieEntry(percentage1.toFloat(),"카페/간식"))
                            }
                            if(percentage2.toFloat() != 0f){
                                entries.add(PieEntry(percentage2.toFloat(),"편의점/마트"))
                            }
                            if(percentage3.toFloat() != 0f){
                                entries.add(PieEntry(percentage3.toFloat(),"술/유흥"))
                            }
                            if(percentage4.toFloat() != 0f){
                                entries.add(PieEntry(percentage4.toFloat(),"쇼핑"))
                            }
                            if(percentage5.toFloat() != 0f){
                                entries.add(PieEntry(percentage5.toFloat(),"취미/여가"))
                            }
                            if(percentage6.toFloat() != 0f){
                                entries.add(PieEntry(percentage6.toFloat(),"건강"))
                            }
                            if(percentage7.toFloat() != 0f){
                                entries.add(PieEntry(percentage7.toFloat(),"주거/통신"))
                            }
                            if(percentage8.toFloat() != 0f){
                                entries.add(PieEntry(percentage8.toFloat(),"보험/금융"))
                            }
                            if(percentage9.toFloat() != 0f){
                                entries.add(PieEntry(percentage9.toFloat(),"미용"))
                            }
                            if(percentage10.toFloat() != 0f){
                                entries.add(PieEntry(percentage10.toFloat(),"교통비"))
                            }
                            if(percentage11.toFloat() != 0f){
                                entries.add(PieEntry(percentage11.toFloat(),"여행/숙박"))
                            }
                            if(percentage12.toFloat() != 0f){
                                entries.add(PieEntry(percentage12.toFloat(),"교육"))
                            }
                            if(percentage13.toFloat() != 0f){
                                entries.add(PieEntry(percentage13.toFloat(),"저축/투자"))
                            }
                            if(percentage14.toFloat() != 0f){
                                entries.add(PieEntry(percentage14.toFloat(),"기타(지출)"))
                            }

                            Log.d("퍼센테이지","${percentage0}")



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

                            //예산값 넣기

                            // 전송은 성공 but 서버 4xx 에러
                            Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                            Log.d("태그: 메시지", "response : ${response.message()}")
                            Log.d("태그: 코드", "response : ${response.code()}")
                        }

                    })



                }


            }






            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }
        binding.spinnerAccountCurrentMonth.setSelection(spinnerPosition)


















        binding.tvMyacountDetail.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(com.example.dongnaefriend_android.R.id.container_fragment,AccountbookDetailFragment()).commit()
            (activity as AccountbookActivity).ForAccountDetailGone()
        }


        return binding.root
    }



}

