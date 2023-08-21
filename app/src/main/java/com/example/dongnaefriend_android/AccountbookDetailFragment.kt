package com.example.dongnaefriend_android

import android.content.Context
import android.content.Intent
import android.graphics.Color
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

    var Year : Int = 0
    var Month : Int = 0
    var Day : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentAccountbookDetailBinding.inflate(layoutInflater)


        var now : Long = System.currentTimeMillis()
        var nowdate : Date = Date(now)
        val dataFormat : DateFormat = SimpleDateFormat("yyyy년 M월 dd일")
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        Year = year
        Month = month+1
        Day = day


        val getTime = dataFormat.format(nowdate)
        binding.dayText.text = getTime


        val calendarView = binding.calendarAccountDetail
        val date : Date = Date(calendarView.date)
        binding.calendarAccountDetail.setOnDateChangeListener{calendarView,year,month,dayOfMonth ->
            var date = "${year}년 ${month+1}월 ${dayOfMonth}일"
            binding.dayText.text = date
            Year = year
            Month = month+1
            Day = dayOfMonth


            api.getMoneyHistory(Year, Month, Day, "Bearer $authToken")
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
                        Log.d("지출수입내역get성공", "response : ${response.body()?.transactions}") // 정상출력

                        var data = response.body()?.transactions!!
                        var size = data.size

                        var data0 = ""
                        var data1 = ""
                        var data2 = ""
                        var data3 = ""
                        var data4 = ""
                        var data5 = ""
                        var data6 = ""
                        var data7 = ""
                        var data8 = ""
                        var data9 = ""

                        if(size == 0){
                            binding.constList1.visibility = View.GONE
                            binding.constList2.visibility = View.GONE
                            binding.constList3.visibility = View.GONE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE
                        }

                        if(size >= 1 ) {

                            binding.constList1.visibility = View.VISIBLE

                            binding.constList2.visibility = View.GONE
                            binding.constList3.visibility = View.GONE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE
                            data0 = data[0].toString()

                            var Data0 = data0.split(",")

                            var type0 = Data0[1]
                            var category0 = Data0[2]
                            var price0 = Data0[6]
                            var memo0 = Data0[8]


                            if(category0 == " transactionCategory=식비"){
                                binding.imgList1.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory1.text ="식비"
                            }
                            else if(category0 == " transactionCategory=카페/간식"){
                                binding.imgList1.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory1.text ="카페/간식"
                            }
                            else if(category0 == " transactionCategory=편의점/마트"){
                                binding.imgList1.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory1.text ="편의점/마트"
                            }
                            else if(category0 == " transactionCategory=술/유흥"){
                                binding.imgList1.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory1.text ="술/유흥"
                            }
                            else if(category0 == " transactionCategory=쇼핑"){
                                binding.imgList1.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory1.text ="쇼핑"
                            }
                            else if(category0 == " transactionCategory=취미/여가"){
                                binding.imgList1.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory1.text ="취미/여가"
                            }
                            else if(category0 == " transactionCategory=건강"){
                                binding.imgList1.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory1.text ="건강"
                            }
                            else if(category0 == " transactionCategory=주거/통신"){
                                binding.imgList1.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory1.text ="주거/통신"
                            }
                            else if(category0 == " transactionCategory=보험/금융"){
                                binding.imgList1.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory1.text ="보험/금융"
                            }
                            else if(category0 == " transactionCategory=미용"){
                                binding.imgList1.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory1.text ="미용"
                            }
                            else if(category0 == " transactionCategory=교통비"){
                                binding.imgList1.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory1.text ="교통비"
                            }
                            else if(category0 == " transactionCategory=여행/숙박"){
                                binding.imgList1.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory1.text ="여행/숙박"
                            }
                            else if(category0 == " transactionCategory=교육"){
                                binding.imgList1.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory1.text ="교육"
                            }
                            else if(category0 == " transactionCategory=저축/투자"){
                                binding.imgList1.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory1.text ="저축/투자"
                            }
                            else if(category0 == " transactionCategory=기타(지출)"){
                                binding.imgList1.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory1.text ="기타(지출)"
                            }
                            else if(category0 == " transactionCategory=월급"){
                                binding.imgList1.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory1.text ="월급"
                            }
                            else if(category0 == " transactionCategory=용돈"){
                                binding.imgList1.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory1.text ="용돈"
                            }
                            else if(category0 == " transactionCategory=이월"){
                                binding.imgList1.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory1.text ="이월"
                            }
                            else if(category0 == " transactionCategory=자산인출"){
                                binding.imgList1.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory1.text ="자산인출"
                            }
                            else if(category0 == " transactionCategory=기타(수입)"){
                                binding.imgList1.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory1.text ="기타(수입)"
                            }

                            var Memo0 = ""
                            if(memo0.length > 20){
                                Memo0 = "메모 없음"
                            }
                            else {
                                var Split = memo0.split("=")
                                Memo0 = Split[1]
                            }

                            binding.tvListmemo1.text = Memo0

                            val split = price0.split("=")
                            price0 = split[1]

                            if(type0 == " type=1"){
                                binding.tvListmoney1.text = "+${price0}원"
                                binding.tvListmoney1.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type0 == " type=0"){
                                binding.tvListmoney1.text = "-${price0}원"
                                binding.tvListmoney1.setTextColor(Color.parseColor("#E05F2E"))
                            }

                        }
                        if(size >= 2) {
                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE


                            binding.constList3.visibility = View.GONE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE


                            data1 = data[1].toString()

                            var Data1 = data1.split(",")

                            var type1 = Data1[1]
                            var category1 = Data1[2]
                            var price1 = Data1[6]
                            var memo1 = Data1[8]


                            if(category1 == " transactionCategory=식비"){
                                binding.imgList2.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory2.text ="식비"
                            }
                            else if(category1 == " transactionCategory=카페/간식"){
                                binding.imgList2.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory2.text ="카페/간식"
                            }
                            else if(category1 == " transactionCategory=편의점/마트"){
                                binding.imgList2.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory2.text ="편의점/마트"
                            }
                            else if(category1 == " transactionCategory=술/유흥"){
                                binding.imgList2.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory2.text ="술/유흥"
                            }
                            else if(category1 == " transactionCategory=쇼핑"){
                                binding.imgList2.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory2.text ="쇼핑"
                            }
                            else if(category1 == " transactionCategory=취미/여가"){
                                binding.imgList2.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory2.text ="취미/여가"
                            }
                            else if(category1 == " transactionCategory=건강"){
                                binding.imgList2.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory2.text ="건강"
                            }
                            else if(category1 == " transactionCategory=주거/통신"){
                                binding.imgList2.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory2.text ="주거/통신"
                            }
                            else if(category1 == " transactionCategory=보험/금융"){
                                binding.imgList2.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory2.text ="보험/금융"
                            }
                            else if(category1 == " transactionCategory=미용"){
                                binding.imgList2.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory2.text ="미용"
                            }
                            else if(category1 == " transactionCategory=교통비"){
                                binding.imgList2.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory2.text ="교통비"
                            }
                            else if(category1 == " transactionCategory=여행/숙박"){
                                binding.imgList2.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory2.text ="여행/숙박"
                            }
                            else if(category1 == " transactionCategory=교육"){
                                binding.imgList2.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory2.text ="교육"
                            }
                            else if(category1 == " transactionCategory=저축/투자"){
                                binding.imgList2.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory2.text ="저축/투자"
                            }
                            else if(category1 == " transactionCategory=기타(지출)"){
                                binding.imgList2.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory2.text ="기타(지출)"
                            }
                            else if(category1 == " transactionCategory=월급"){
                                binding.imgList2.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory2.text ="월급"
                            }
                            else if(category1 == " transactionCategory=용돈"){
                                binding.imgList2.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory2.text ="용돈"
                            }
                            else if(category1 == " transactionCategory=이월"){
                                binding.imgList2.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory2.text ="이월"
                            }
                            else if(category1 == " transactionCategory=자산인출"){
                                binding.imgList2.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory2.text ="자산인출"
                            }
                            else if(category1 == " transactionCategory=기타(수입)"){
                                binding.imgList2.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory2.text ="기타(수입)"
                            }

                            var Memo1 = ""
                            if(memo1.length > 20){
                                Memo1 = "메모 없음"
                            }
                            else {
                                var Split = memo1.split("=")
                                Memo1 = Split[1]
                            }
                            binding.tvListmemo2.text = Memo1

                            val split = price1.split("=")
                            price1 = split[1]

                            if(type1 == " type=1"){
                                binding.tvListmoney2.text = "+${price1}원"
                                binding.tvListmoney2.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type1 == " type=0"){
                                binding.tvListmoney2.text = "-${price1}원"
                                binding.tvListmoney2.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 3 ) {

                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.VISIBLE

                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE

                            data2 = data[2].toString()

                            var Data2 = data2.split(",")

                            var type2 = Data2[1]
                            var category2 = Data2[2]
                            var price2 = Data2[6]
                            var memo2 = Data2[8]


                            if(category2 == " transactionCategory=식비"){
                                binding.imgList3.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory3.text ="식비"
                            }
                            else if(category2 == " transactionCategory=카페/간식"){
                                binding.imgList3.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory3.text ="카페/간식"
                            }
                            else if(category2 == " transactionCategory=편의점/마트"){
                                binding.imgList3.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory3.text ="편의점/마트"
                            }
                            else if(category2 == " transactionCategory=술/유흥"){
                                binding.imgList3.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory3.text ="술/유흥"
                            }
                            else if(category2 == " transactionCategory=쇼핑"){
                                binding.imgList3.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory3.text ="쇼핑"
                            }
                            else if(category2 == " transactionCategory=취미/여가"){
                                binding.imgList3.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory3.text ="취미/여가"
                            }
                            else if(category2 == " transactionCategory=건강"){
                                binding.imgList3.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory3.text ="건강"
                            }
                            else if(category2 == " transactionCategory=주거/통신"){
                                binding.imgList3.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory3.text ="주거/통신"
                            }
                            else if(category2 == " transactionCategory=보험/금융"){
                                binding.imgList3.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory3.text ="보험/금융"
                            }
                            else if(category2 == " transactionCategory=미용"){
                                binding.imgList3.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory3.text ="미용"
                            }
                            else if(category2 == " transactionCategory=교통비"){
                                binding.imgList3.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory3.text ="교통비"
                            }
                            else if(category2 == " transactionCategory=여행/숙박"){
                                binding.imgList3.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory3.text ="여행/숙박"
                            }
                            else if(category2 == " transactionCategory=교육"){
                                binding.imgList3.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory3.text ="교육"
                            }
                            else if(category2 == " transactionCategory=저축/투자"){
                                binding.imgList3.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory3.text ="저축/투자"
                            }
                            else if(category2 == " transactionCategory=기타(지출)"){
                                binding.imgList3.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory3.text ="기타(지출)"
                            }
                            else if(category2 == " transactionCategory=월급"){
                                binding.imgList3.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory3.text ="월급"
                            }
                            else if(category2 == " transactionCategory=용돈"){
                                binding.imgList3.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory3.text ="용돈"
                            }
                            else if(category2 == " transactionCategory=이월"){
                                binding.imgList3.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory3.text ="이월"
                            }
                            else if(category2 == " transactionCategory=자산인출"){
                                binding.imgList3.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory3.text ="자산인출"
                            }
                            else if(category2 == " transactionCategory=기타(수입)"){
                                binding.imgList3.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory3.text ="기타(수입)"
                            }

                            var Memo2 = ""
                            if(memo2.length > 20){
                                Memo2 = "메모 없음"
                            }
                            else {
                                var Split = memo2.split("=")
                                Memo2 = Split[1]
                            }
                            binding.tvListmemo3.text = Memo2

                            val split = price2.split("=")
                            price2 = split[1]

                            if(type2 == " type=1"){
                                binding.tvListmoney3.text = "+${price2}원"
                                binding.tvListmoney3.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type2 == " type=0"){
                                binding.tvListmoney3.text = "-${price2}원"
                                binding.tvListmoney3.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 4 ) {

                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.VISIBLE
                            binding.constList4.visibility = View.VISIBLE

                            binding.constList5.visibility = View.GONE

                            data3 = data[3].toString()

                            var Data3 = data3.split(",")

                            var type3 = Data3[1]
                            var category3 = Data3[2]
                            var price3 = Data3[6]
                            var memo3 = Data3[8]


                            if(category3 == " transactionCategory=식비"){
                                binding.imgList4.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory4.text ="식비"
                            }
                            else if(category3 == " transactionCategory=카페/간식"){
                                binding.imgList4.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory4.text ="카페/간식"
                            }
                            else if(category3 == " transactionCategory=편의점/마트"){
                                binding.imgList4.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory4.text ="편의점/마트"
                            }
                            else if(category3 == " transactionCategory=술/유흥"){
                                binding.imgList4.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory4.text ="술/유흥"
                            }
                            else if(category3 == " transactionCategory=쇼핑"){
                                binding.imgList4.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory4.text ="쇼핑"
                            }
                            else if(category3 == " transactionCategory=취미/여가"){
                                binding.imgList4.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory4.text ="취미/여가"
                            }
                            else if(category3 == " transactionCategory=건강"){
                                binding.imgList4.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory4.text ="건강"
                            }
                            else if(category3 == " transactionCategory=주거/통신"){
                                binding.imgList4.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory4.text ="주거/통신"
                            }
                            else if(category3 == " transactionCategory=보험/금융"){
                                binding.imgList4.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory4.text ="보험/금융"
                            }
                            else if(category3 == " transactionCategory=미용"){
                                binding.imgList4.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory4.text ="미용"
                            }
                            else if(category3 == " transactionCategory=교통비"){
                                binding.imgList4.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory4.text ="교통비"
                            }
                            else if(category3 == " transactionCategory=여행/숙박"){
                                binding.imgList4.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory4.text ="여행/숙박"
                            }
                            else if(category3 == " transactionCategory=교육"){
                                binding.imgList4.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory4.text ="교육"
                            }
                            else if(category3 == " transactionCategory=저축/투자"){
                                binding.imgList4.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory4.text ="저축/투자"
                            }
                            else if(category3 == " transactionCategory=기타(지출)"){
                                binding.imgList4.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory4.text ="기타(지출)"
                            }
                            else if(category3 == " transactionCategory=월급"){
                                binding.imgList4.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory4.text ="월급"
                            }
                            else if(category3 == " transactionCategory=용돈"){
                                binding.imgList4.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory4.text ="용돈"
                            }
                            else if(category3 == " transactionCategory=이월"){
                                binding.imgList4.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory4.text ="이월"
                            }
                            else if(category3 == " transactionCategory=자산인출"){
                                binding.imgList4.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory4.text ="자산인출"
                            }
                            else if(category3 == " transactionCategory=기타(수입)"){
                                binding.imgList4.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory4.text ="기타(수입)"
                            }

                            var Memo3 = ""
                            if(memo3.length > 20){
                                Memo3 = "메모 없음"
                            }
                            else {
                                var Split = memo3.split("=")
                                Memo3 = Split[1]
                            }
                            binding.tvListmemo4.text = Memo3

                            val split = price3.split("=")
                            price3 = split[1]

                            if(type3 == " type=1"){
                                binding.tvListmoney4.text = "+${price3}원"
                                binding.tvListmoney4.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type3 == " type=0"){
                                binding.tvListmoney4.text = "-${price3}원"
                                binding.tvListmoney4.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 5 ) {

                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.VISIBLE
                            binding.constList4.visibility = View.VISIBLE
                            binding.constList5.visibility = View.VISIBLE

                            data4 = data[4].toString()

                            var Data4 = data4.split(",")

                            var type4 = Data4[1]
                            var category4 = Data4[2]
                            var price4 = Data4[6]
                            var memo4 = Data4[8]


                            if(category4 == " transactionCategory=식비"){
                                binding.imgList5.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory5.text ="식비"
                            }
                            else if(category4 == " transactionCategory=카페/간식"){
                                binding.imgList5.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory5.text ="카페/간식"
                            }
                            else if(category4 == " transactionCategory=편의점/마트"){
                                binding.imgList5.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory5.text ="편의점/마트"
                            }
                            else if(category4 == " transactionCategory=술/유흥"){
                                binding.imgList5.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory5.text ="술/유흥"
                            }
                            else if(category4 == " transactionCategory=쇼핑"){
                                binding.imgList5.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory5.text ="쇼핑"
                            }
                            else if(category4 == " transactionCategory=취미/여가"){
                                binding.imgList5.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory5.text ="취미/여가"
                            }
                            else if(category4 == " transactionCategory=건강"){
                                binding.imgList5.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory5.text ="건강"
                            }
                            else if(category4 == " transactionCategory=주거/통신"){
                                binding.imgList5.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory5.text ="주거/통신"
                            }
                            else if(category4 == " transactionCategory=보험/금융"){
                                binding.imgList5.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory5.text ="보험/금융"
                            }
                            else if(category4 == " transactionCategory=미용"){
                                binding.imgList5.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory5.text ="미용"
                            }
                            else if(category4 == " transactionCategory=교통비"){
                                binding.imgList5.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory5.text ="교통비"
                            }
                            else if(category4 == " transactionCategory=여행/숙박"){
                                binding.imgList5.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory5.text ="여행/숙박"
                            }
                            else if(category4 == " transactionCategory=교육"){
                                binding.imgList5.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory5.text ="교육"
                            }
                            else if(category4 == " transactionCategory=저축/투자"){
                                binding.imgList5.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory5.text ="저축/투자"
                            }
                            else if(category4 == " transactionCategory=기타(지출)"){
                                binding.imgList5.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory5.text ="기타(지출)"
                            }
                            else if(category4 == " transactionCategory=월급"){
                                binding.imgList5.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory5.text ="월급"
                            }
                            else if(category4 == " transactionCategory=용돈"){
                                binding.imgList5.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory5.text ="용돈"
                            }
                            else if(category4 == " transactionCategory=이월"){
                                binding.imgList5.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory5.text ="이월"
                            }
                            else if(category4 == " transactionCategory=자산인출"){
                                binding.imgList5.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory5.text ="자산인출"
                            }
                            else if(category4 == " transactionCategory=기타(수입)"){
                                binding.imgList5.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory5.text ="기타(수입)"
                            }

                            var Memo4 = ""
                            if(memo4.length > 20){
                                Memo4 = "메모 없음"
                            }
                            else {
                                var Split = memo4.split("=")
                                Memo4 = Split[1]
                            }

                            binding.tvListmemo5.text = Memo4

                            val split = price4.split("=")
                            price4 = split[1]

                            if(type4 == " type=1"){
                                binding.tvListmoney5.text = "+${price4}원"
                                binding.tvListmoney5.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type4 == " type=0"){
                                binding.tvListmoney5.text = "-${price4}원"
                                binding.tvListmoney5.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 6 ) {
                            data5 = data[5].toString()
                        }
                        if(size >= 7 ) {
                            data6 = data[6].toString()
                        }
                        if(size >= 8 ) {
                            data7 = data[7].toString()
                        }
                        if(size >= 9 ) {
                            data8 = data[8].toString()
                        }
                        if(size >= 10 ) {
                            data9 = data[9].toString()
                        }


                        // 전송은 성공 but 서버 4xx 에러
                        Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                        Log.d("태그: 메시지", "response : ${response.message()}")
                        Log.d("태그: 코드", "response : ${response.code()}")
                    }

                })
        }






        Runnable {
            api.getMoneyHistory(Year, Month, Day, "Bearer $authToken")
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


                        Log.d("지출수입내역get성공", "response : ${response.body()?.transactions}") // 정상출력




                        var data = response.body()?.transactions!!
                        var size = data.size

                        var data0 = ""
                        var data1 = ""
                        var data2 = ""
                        var data3 = ""
                        var data4 = ""
                        var data5 = ""
                        var data6 = ""
                        var data7 = ""
                        var data8 = ""
                        var data9 = ""


                        if(size == 0){
                            binding.constList1.visibility = View.GONE
                            binding.constList2.visibility = View.GONE
                            binding.constList3.visibility = View.GONE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE
                        }
                        if(size >= 1 ) {

                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.GONE
                            binding.constList3.visibility = View.GONE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE
                            data0 = data[0].toString()

                            var Data0 = data0.split(",")

                            var type0 = Data0[1]
                            var category0 = Data0[2]
                            var price0 = Data0[6]
                            var memo0 = Data0[8]


                            if(category0 == " transactionCategory=식비"){
                                binding.imgList1.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory1.text ="식비"
                            }
                            else if(category0 == " transactionCategory=카페/간식"){
                                binding.imgList1.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory1.text ="카페/간식"
                            }
                            else if(category0 == " transactionCategory=편의점/마트"){
                                binding.imgList1.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory1.text ="편의점/마트"
                            }
                            else if(category0 == " transactionCategory=술/유흥"){
                                binding.imgList1.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory1.text ="술/유흥"
                            }
                            else if(category0 == " transactionCategory=쇼핑"){
                                binding.imgList1.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory1.text ="쇼핑"
                            }
                            else if(category0 == " transactionCategory=취미/여가"){
                                binding.imgList1.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory1.text ="취미/여가"
                            }
                            else if(category0 == " transactionCategory=건강"){
                                binding.imgList1.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory1.text ="건강"
                            }
                            else if(category0 == " transactionCategory=주거/통신"){
                                binding.imgList1.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory1.text ="주거/통신"
                            }
                            else if(category0 == " transactionCategory=보험/금융"){
                                binding.imgList1.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory1.text ="보험/금융"
                            }
                            else if(category0 == " transactionCategory=미용"){
                                binding.imgList1.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory1.text ="미용"
                            }
                            else if(category0 == " transactionCategory=교통비"){
                                binding.imgList1.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory1.text ="교통비"
                            }
                            else if(category0 == " transactionCategory=여행/숙박"){
                                binding.imgList1.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory1.text ="여행/숙박"
                            }
                            else if(category0 == " transactionCategory=교육"){
                                binding.imgList1.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory1.text ="교육"
                            }
                            else if(category0 == " transactionCategory=저축/투자"){
                                binding.imgList1.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory1.text ="저축/투자"
                            }
                            else if(category0 == " transactionCategory=기타(지출)"){
                                binding.imgList1.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory1.text ="기타(지출)"
                            }
                            else if(category0 == " transactionCategory=월급"){
                                binding.imgList1.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory1.text ="월급"
                            }
                            else if(category0 == " transactionCategory=용돈"){
                                binding.imgList1.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory1.text ="용돈"
                            }
                            else if(category0 == " transactionCategory=이월"){
                                binding.imgList1.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory1.text ="이월"
                            }
                            else if(category0 == " transactionCategory=자산인출"){
                                binding.imgList1.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory1.text ="자산인출"
                            }
                            else if(category0 == " transactionCategory=기타(수입)"){
                                binding.imgList1.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory1.text ="기타(수입)"
                            }

                            var Memo0 = ""
                            if(memo0.length > 20){
                                Memo0 = "메모 없음"
                            }
                            else {
                                var Split = memo0.split("=")
                                Memo0 = Split[1]
                            }

                            binding.tvListmemo1.text = Memo0

                            val split = price0.split("=")
                            price0 = split[1]

                            if(type0 == " type=1"){
                                binding.tvListmoney1.text = "+${price0}원"
                                binding.tvListmoney1.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type0 == " type=0"){
                                binding.tvListmoney1.text = "-${price0}원"
                                binding.tvListmoney1.setTextColor(Color.parseColor("#E05F2E"))
                            }

                        }
                        if(size >= 2) {
                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.GONE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE

                            data1 = data[1].toString()

                            var Data1 = data1.split(",")

                            var type1 = Data1[1]
                            var category1 = Data1[2]
                            var price1 = Data1[6]
                            var memo1 = Data1[8]


                            if(category1 == " transactionCategory=식비"){
                                binding.imgList2.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory2.text ="식비"
                            }
                            else if(category1 == " transactionCategory=카페/간식"){
                                binding.imgList2.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory2.text ="카페/간식"
                            }
                            else if(category1 == " transactionCategory=편의점/마트"){
                                binding.imgList2.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory2.text ="편의점/마트"
                            }
                            else if(category1 == " transactionCategory=술/유흥"){
                                binding.imgList2.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory2.text ="술/유흥"
                            }
                            else if(category1 == " transactionCategory=쇼핑"){
                                binding.imgList2.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory2.text ="쇼핑"
                            }
                            else if(category1 == " transactionCategory=취미/여가"){
                                binding.imgList2.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory2.text ="취미/여가"
                            }
                            else if(category1 == " transactionCategory=건강"){
                                binding.imgList2.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory2.text ="건강"
                            }
                            else if(category1 == " transactionCategory=주거/통신"){
                                binding.imgList2.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory2.text ="주거/통신"
                            }
                            else if(category1 == " transactionCategory=보험/금융"){
                                binding.imgList2.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory2.text ="보험/금융"
                            }
                            else if(category1 == " transactionCategory=미용"){
                                binding.imgList2.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory2.text ="미용"
                            }
                            else if(category1 == " transactionCategory=교통비"){
                                binding.imgList2.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory2.text ="교통비"
                            }
                            else if(category1 == " transactionCategory=여행/숙박"){
                                binding.imgList2.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory2.text ="여행/숙박"
                            }
                            else if(category1 == " transactionCategory=교육"){
                                binding.imgList2.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory2.text ="교육"
                            }
                            else if(category1 == " transactionCategory=저축/투자"){
                                binding.imgList2.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory2.text ="저축/투자"
                            }
                            else if(category1 == " transactionCategory=기타(지출)"){
                                binding.imgList2.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory2.text ="기타(지출)"
                            }
                            else if(category1 == " transactionCategory=월급"){
                                binding.imgList2.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory2.text ="월급"
                            }
                            else if(category1 == " transactionCategory=용돈"){
                                binding.imgList2.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory2.text ="용돈"
                            }
                            else if(category1 == " transactionCategory=이월"){
                                binding.imgList2.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory2.text ="이월"
                            }
                            else if(category1 == " transactionCategory=자산인출"){
                                binding.imgList2.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory2.text ="자산인출"
                            }
                            else if(category1 == " transactionCategory=기타(수입)"){
                                binding.imgList2.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory2.text ="기타(수입)"
                            }

                            var Memo1 = ""
                            if(memo1.length > 20){
                                Memo1 = "메모 없음"
                            }
                            else {
                                var Split = memo1.split("=")
                                Memo1 = Split[1]
                            }
                            binding.tvListmemo2.text = Memo1

                            val split = price1.split("=")
                            price1 = split[1]

                            if(type1 == " type=1"){
                                binding.tvListmoney2.text = "+${price1}원"
                                binding.tvListmoney2.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type1 == " type=0"){
                                binding.tvListmoney2.text = "-${price1}원"
                                binding.tvListmoney2.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 3 ) {
                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.VISIBLE
                            binding.constList4.visibility = View.GONE
                            binding.constList5.visibility = View.GONE

                            data2 = data[2].toString()

                            var Data2 = data2.split(",")

                            var type2 = Data2[1]
                            var category2 = Data2[2]
                            var price2 = Data2[6]
                            var memo2 = Data2[8]


                            if(category2 == " transactionCategory=식비"){
                                binding.imgList3.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory3.text ="식비"
                            }
                            else if(category2 == " transactionCategory=카페/간식"){
                                binding.imgList3.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory3.text ="카페/간식"
                            }
                            else if(category2 == " transactionCategory=편의점/마트"){
                                binding.imgList3.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory3.text ="편의점/마트"
                            }
                            else if(category2 == " transactionCategory=술/유흥"){
                                binding.imgList3.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory3.text ="술/유흥"
                            }
                            else if(category2 == " transactionCategory=쇼핑"){
                                binding.imgList3.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory3.text ="쇼핑"
                            }
                            else if(category2 == " transactionCategory=취미/여가"){
                                binding.imgList3.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory3.text ="취미/여가"
                            }
                            else if(category2 == " transactionCategory=건강"){
                                binding.imgList3.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory3.text ="건강"
                            }
                            else if(category2 == " transactionCategory=주거/통신"){
                                binding.imgList3.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory3.text ="주거/통신"
                            }
                            else if(category2 == " transactionCategory=보험/금융"){
                                binding.imgList3.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory3.text ="보험/금융"
                            }
                            else if(category2 == " transactionCategory=미용"){
                                binding.imgList3.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory3.text ="미용"
                            }
                            else if(category2 == " transactionCategory=교통비"){
                                binding.imgList3.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory3.text ="교통비"
                            }
                            else if(category2 == " transactionCategory=여행/숙박"){
                                binding.imgList3.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory3.text ="여행/숙박"
                            }
                            else if(category2 == " transactionCategory=교육"){
                                binding.imgList3.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory3.text ="교육"
                            }
                            else if(category2 == " transactionCategory=저축/투자"){
                                binding.imgList3.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory3.text ="저축/투자"
                            }
                            else if(category2 == " transactionCategory=기타(지출)"){
                                binding.imgList3.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory3.text ="기타(지출)"
                            }
                            else if(category2 == " transactionCategory=월급"){
                                binding.imgList3.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory3.text ="월급"
                            }
                            else if(category2 == " transactionCategory=용돈"){
                                binding.imgList3.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory3.text ="용돈"
                            }
                            else if(category2 == " transactionCategory=이월"){
                                binding.imgList3.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory3.text ="이월"
                            }
                            else if(category2 == " transactionCategory=자산인출"){
                                binding.imgList3.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory3.text ="자산인출"
                            }
                            else if(category2 == " transactionCategory=기타(수입)"){
                                binding.imgList3.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory3.text ="기타(수입)"
                            }

                            var Memo2 = ""
                            if(memo2.length > 20){
                                Memo2 = "메모 없음"
                            }
                            else {
                                var Split = memo2.split("=")
                                Memo2 = Split[1]
                            }
                            binding.tvListmemo3.text = Memo2

                            val split = price2.split("=")
                            price2 = split[1]

                            if(type2 == " type=1"){
                                binding.tvListmoney3.text = "+${price2}원"
                                binding.tvListmoney3.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type2 == " type=0"){
                                binding.tvListmoney3.text = "-${price2}원"
                                binding.tvListmoney3.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 4 ) {
                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.VISIBLE
                            binding.constList4.visibility = View.VISIBLE
                            binding.constList5.visibility = View.GONE

                            data3 = data[3].toString()

                            var Data3 = data3.split(",")

                            var type3 = Data3[1]
                            var category3 = Data3[2]
                            var price3 = Data3[6]
                            var memo3 = Data3[8]


                            if(category3 == " transactionCategory=식비"){
                                binding.imgList4.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory4.text ="식비"
                            }
                            else if(category3 == " transactionCategory=카페/간식"){
                                binding.imgList4.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory4.text ="카페/간식"
                            }
                            else if(category3 == " transactionCategory=편의점/마트"){
                                binding.imgList4.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory4.text ="편의점/마트"
                            }
                            else if(category3 == " transactionCategory=술/유흥"){
                                binding.imgList4.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory4.text ="술/유흥"
                            }
                            else if(category3 == " transactionCategory=쇼핑"){
                                binding.imgList4.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory4.text ="쇼핑"
                            }
                            else if(category3 == " transactionCategory=취미/여가"){
                                binding.imgList4.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory4.text ="취미/여가"
                            }
                            else if(category3 == " transactionCategory=건강"){
                                binding.imgList4.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory4.text ="건강"
                            }
                            else if(category3 == " transactionCategory=주거/통신"){
                                binding.imgList4.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory4.text ="주거/통신"
                            }
                            else if(category3 == " transactionCategory=보험/금융"){
                                binding.imgList4.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory4.text ="보험/금융"
                            }
                            else if(category3 == " transactionCategory=미용"){
                                binding.imgList4.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory4.text ="미용"
                            }
                            else if(category3 == " transactionCategory=교통비"){
                                binding.imgList4.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory4.text ="교통비"
                            }
                            else if(category3 == " transactionCategory=여행/숙박"){
                                binding.imgList4.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory4.text ="여행/숙박"
                            }
                            else if(category3 == " transactionCategory=교육"){
                                binding.imgList4.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory4.text ="교육"
                            }
                            else if(category3 == " transactionCategory=저축/투자"){
                                binding.imgList4.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory4.text ="저축/투자"
                            }
                            else if(category3 == " transactionCategory=기타(지출)"){
                                binding.imgList4.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory4.text ="기타(지출)"
                            }
                            else if(category3 == " transactionCategory=월급"){
                                binding.imgList4.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory4.text ="월급"
                            }
                            else if(category3 == " transactionCategory=용돈"){
                                binding.imgList4.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory4.text ="용돈"
                            }
                            else if(category3 == " transactionCategory=이월"){
                                binding.imgList4.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory4.text ="이월"
                            }
                            else if(category3 == " transactionCategory=자산인출"){
                                binding.imgList4.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory4.text ="자산인출"
                            }
                            else if(category3 == " transactionCategory=기타(수입)"){
                                binding.imgList4.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory4.text ="기타(수입)"
                            }

                            var Memo3 = ""
                            if(memo3.length > 20){
                                Memo3 = "메모 없음"
                            }
                            else {
                                var Split = memo3.split("=")
                                Memo3 = Split[1]
                            }
                            binding.tvListmemo4.text = Memo3

                            val split = price3.split("=")
                            price3 = split[1]

                            if(type3 == " type=1"){
                                binding.tvListmoney4.text = "+${price3}원"
                                binding.tvListmoney4.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type3 == " type=0"){
                                binding.tvListmoney4.text = "-${price3}원"
                                binding.tvListmoney4.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 5 ) {
                            binding.constList1.visibility = View.VISIBLE
                            binding.constList2.visibility = View.VISIBLE
                            binding.constList3.visibility = View.VISIBLE
                            binding.constList4.visibility = View.VISIBLE
                            binding.constList5.visibility = View.VISIBLE

                            data4 = data[4].toString()

                            var Data4 = data4.split(",")

                            var type4 = Data4[1]
                            var category4 = Data4[2]
                            var price4 = Data4[6]
                            var memo4 = Data4[8]


                            if(category4 == " transactionCategory=식비"){
                                binding.imgList5.setImageResource(R.drawable.img_category_food)
                                binding.tvListcategory5.text ="식비"
                            }
                            else if(category4 == " transactionCategory=카페/간식"){
                                binding.imgList5.setImageResource(R.drawable.img_category_snack)
                                binding.tvListcategory5.text ="카페/간식"
                            }
                            else if(category4 == " transactionCategory=편의점/마트"){
                                binding.imgList5.setImageResource(R.drawable.img_category_mart)
                                binding.tvListcategory5.text ="편의점/마트"
                            }
                            else if(category4 == " transactionCategory=술/유흥"){
                                binding.imgList5.setImageResource(R.drawable.img_category_alchol)
                                binding.tvListcategory5.text ="술/유흥"
                            }
                            else if(category4 == " transactionCategory=쇼핑"){
                                binding.imgList5.setImageResource(R.drawable.img_category_shopping)
                                binding.tvListcategory5.text ="쇼핑"
                            }
                            else if(category4 == " transactionCategory=취미/여가"){
                                binding.imgList5.setImageResource(R.drawable.img_category_hobby)
                                binding.tvListcategory5.text ="취미/여가"
                            }
                            else if(category4 == " transactionCategory=건강"){
                                binding.imgList5.setImageResource(R.drawable.img_category_health)
                                binding.tvListcategory5.text ="건강"
                            }
                            else if(category4 == " transactionCategory=주거/통신"){
                                binding.imgList5.setImageResource(R.drawable.img_category_living)
                                binding.tvListcategory5.text ="주거/통신"
                            }
                            else if(category4 == " transactionCategory=보험/금융"){
                                binding.imgList5.setImageResource(R.drawable.img_category_fund)
                                binding.tvListcategory5.text ="보험/금융"
                            }
                            else if(category4 == " transactionCategory=미용"){
                                binding.imgList5.setImageResource(R.drawable.img_category_beauty)
                                binding.tvListcategory5.text ="미용"
                            }
                            else if(category4 == " transactionCategory=교통비"){
                                binding.imgList5.setImageResource(R.drawable.img_category_transport)
                                binding.tvListcategory5.text ="교통비"
                            }
                            else if(category4 == " transactionCategory=여행/숙박"){
                                binding.imgList5.setImageResource(R.drawable.img_category_trip)
                                binding.tvListcategory5.text ="여행/숙박"
                            }
                            else if(category4 == " transactionCategory=교육"){
                                binding.imgList5.setImageResource(R.drawable.img_category_education)
                                binding.tvListcategory5.text ="교육"
                            }
                            else if(category4 == " transactionCategory=저축/투자"){
                                binding.imgList5.setImageResource(R.drawable.img_category_bank)
                                binding.tvListcategory5.text ="저축/투자"
                            }
                            else if(category4 == " transactionCategory=기타(지출)"){
                                binding.imgList5.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory5.text ="기타(지출)"
                            }
                            else if(category4 == " transactionCategory=월급"){
                                binding.imgList5.setImageResource(R.drawable.img_category_salery)
                                binding.tvListcategory5.text ="월급"
                            }
                            else if(category4 == " transactionCategory=용돈"){
                                binding.imgList5.setImageResource(R.drawable.img_category_allowance)
                                binding.tvListcategory5.text ="용돈"
                            }
                            else if(category4 == " transactionCategory=이월"){
                                binding.imgList5.setImageResource(R.drawable.img_category_carryover)
                                binding.tvListcategory5.text ="이월"
                            }
                            else if(category4 == " transactionCategory=자산인출"){
                                binding.imgList5.setImageResource(R.drawable.img_category_withdraw)
                                binding.tvListcategory5.text ="자산인출"
                            }
                            else if(category4 == " transactionCategory=기타(수입)"){
                                binding.imgList5.setImageResource(R.drawable.img_category_etc)
                                binding.tvListcategory5.text ="기타(수입)"
                            }

                            var Memo4 = ""
                            if(memo4.length > 20){
                                Memo4 = "메모 없음"
                            }
                            else {
                                var Split = memo4.split("=")
                                Memo4 = Split[1]
                            }

                            binding.tvListmemo5.text = Memo4

                            val split = price4.split("=")
                            price4 = split[1]

                            if(type4 ==" type=1" ){
                                binding.tvListmoney5.text = "+${price4}원"
                                binding.tvListmoney5.setTextColor(Color.parseColor("#49A2ED"))
                            }
                            else if(type4 == " type=0"){
                                binding.tvListmoney5.text = "-${price4}원"
                                binding.tvListmoney5.setTextColor(Color.parseColor("#E05F2E"))
                            }
                        }
                        if(size >= 6 ) {
                            data5 = data[5].toString()
                        }
                        if(size >= 7 ) {
                            data6 = data[6].toString()
                        }
                        if(size >= 8 ) {
                            data7 = data[7].toString()
                        }
                        if(size >= 9 ) {
                            data8 = data[8].toString()
                        }
                        if(size >= 10 ) {
                            data9 = data[9].toString()
                        }















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





        binding.btnBackButton.setOnClickListener{
            val intent = Intent(getActivity(), AccountbookActivity::class.java)
            startActivity(intent)
        }





    }














}