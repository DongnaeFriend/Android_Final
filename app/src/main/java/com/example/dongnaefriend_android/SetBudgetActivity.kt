package com.example.dongnaefriend_android


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.dongnaefriend_android.databinding.ActivitySetBudgetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
private val api: RetrofitInterface = retrofit.create(RetrofitInterface::class.java) // retrofit이 interface 구현
private val authToken = "토큰값을 여기 작성"



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



        Runnable {
            api.getBudget(15000, "Bearer $authToken").enqueue(object : Callback<BudgetResponse> {
                // 전송 실패
                override fun onFailure(call: Call<BudgetResponse>, t: Throwable) {
                    Log.d("************", t.message!!)
                }
                // 전송 성공
                override fun onResponse(call: Call<BudgetResponse>, response: Response<BudgetResponse>) {
                    Log.d("*******&&&&&*****", "response : ${response.body()?.data}") // 정상출력

                    // 전송은 성공 but 서버 4xx 에러
                    Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                    Log.d("태그: 메시지", "response : ${response.message()}")
                    Log.d("태그: 코드", "response : ${response.code()}")
                }
            })
        }.run()




    }
}