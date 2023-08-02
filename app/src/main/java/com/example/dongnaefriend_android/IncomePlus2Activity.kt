package com.example.dongnaefriend_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityIncomePlus2Binding

private lateinit var binding:ActivityIncomePlus2Binding

class IncomePlus2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomePlus2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //닫기 버튼
        binding.imageviewIncomeclose2.setOnClickListener{
            val intent = Intent(this,AccountbookActivity::class.java)
            startActivity(intent)
        }
        //뒤로가기 버튼
        binding.imageviewIncomegoback2.setOnClickListener {
            val intent = Intent(this,IncomePlusActivity::class.java)
            startActivity(intent)
        }


        //지출내역 선택
        binding.imageviewAccountcategorySalery.setOnClickListener{
            val intent = Intent(this,IncomePlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryAllowance.setOnClickListener{
            val intent = Intent(this,IncomePlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryCarryover.setOnClickListener{
            val intent = Intent(this,IncomePlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryWithdraw.setOnClickListener{
            val intent = Intent(this,IncomePlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryEtc.setOnClickListener{
            val intent = Intent(this,IncomePlus3Activity::class.java)
            startActivity(intent)
        }
    }
}