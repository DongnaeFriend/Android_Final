package com.example.dongnaefriend_android

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dongnaefriend_android.Retrofit2.PostMoneyHistory
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.databinding.ActivityIncomePlus3Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private lateinit var binding:ActivityIncomePlus3Binding


private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
private val api: RetrofitInterfaceTommy = retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
//private val authToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"

class IncomePlus3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomePlus3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var moneyAmount = intent.getIntExtra("moneyAmount", 0)
        var category = intent.getIntExtra("category",0)
        var year = intent.getIntExtra("year",0)
        var month = intent.getIntExtra("month",0)
        var day = intent.getIntExtra("day",0)
        var memo = intent.getStringExtra("memo")!!

        binding.tvMoneyamount.text = moneyAmount.toString()

        binding.imageviewIncomeclose3.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }

        binding.imageviewIncomegoback3.setOnClickListener {
            val intent = Intent(this,IncomePlus2Activity::class.java)
            startActivity(intent)
        }




        binding.imageviewIncomemethodCash.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
            val data = PostMoneyHistory(1,category,year,month,day,moneyAmount,0,memo)
            api.postMoneyHistory(data,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("**********", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "포스트 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "연결 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })
        }
        binding.imageviewIncomemethodCheckcard.setOnClickListener {

            val data = PostMoneyHistory(1,category,year,month,day,moneyAmount,1,memo)
            api.postMoneyHistory(data,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("**********", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "포스트 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "연결 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewIncomemethodCrditcard.setOnClickListener {

            val data = PostMoneyHistory(1,category,year,month,day,moneyAmount,2,memo)
            api.postMoneyHistory(data,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("**********", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "포스트 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "연결 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
    }
}