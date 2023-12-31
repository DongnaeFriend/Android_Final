package com.example.dongnaefriend_android


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.dongnaefriend_android.Retrofit2.BudgetResponse
import com.example.dongnaefriend_android.Retrofit2.PostBudget
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.databinding.ActivitySetBudgetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
private val api: RetrofitInterfaceTommy = retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
//private val authToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"



class SetBudgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetBudgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivSetBudgetGoBack.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.ivSetBudgetSave.setOnClickListener {

            var budget = binding.etSetbudget.text.toString()
            var Budget = budget.toInt()



            //retrofit통신 - Post
            api.postBudget(2023,7,Budget,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("예산설정 성공", "response :")



                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(TAG, "예산실패")
                    Log.e(TAG, t.toString())
                }
            })




            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }



        binding.etSetbudget.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                var setBudget = binding.etSetbudget.text.toString()
                var setBudgetInt = setBudget.toInt()
                binding.tvBudgetperday.text = (setBudgetInt / 30).toString()
            }
        })




        //retrofit 통신 https://velog.io/@hygge/Android-Kotlin-Retrofit2%EB%A1%9C-%EC%84%9C%EB%B2%84-%ED%86%B5%EC%8B%A0-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0 참고
        Runnable {
            api.getBudget(2023, 8,"Bearer $authToken").enqueue(object : Callback<BudgetResponse> {
                // 전송 실패
                override fun onFailure(call: Call<BudgetResponse>, t: Throwable) {
                    Log.d("************", t.message!!)
                }
                // 전송 성공
                override fun onResponse(call: Call<BudgetResponse>, response: Response<BudgetResponse>) {
                    Log.d("*******&&&&&*****", "response : ${response.body()?.budget}") // 정상출력

                    // 전송은 성공 but 서버 4xx 에러
                    Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                    Log.d("태그: 메시지", "response : ${response.message()}")
                    Log.d("태그: 코드", "response : ${response.code()}")
                }
            })
        }.run()




    }
}