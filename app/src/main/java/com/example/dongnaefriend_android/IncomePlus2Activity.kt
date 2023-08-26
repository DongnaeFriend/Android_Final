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

        var moneyAmount = intent.getIntExtra("text", 0)
        var year = intent.getIntExtra("year",100).toInt()
        var month = intent.getIntExtra("month",100).toInt()
        var day = intent.getIntExtra("day",100).toInt()

        var memo = binding.edittextIncomeplusmemo.text.toString()

        //닫기 버튼
        binding.imageviewIncomeclose2.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java)
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
            var memo = binding.edittextIncomeplusmemo.text.toString()

            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",15)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)

            startActivity(intent)
        }
        binding.imageviewAccountcategoryAllowance.setOnClickListener{
            val intent = Intent(this,IncomePlus3Activity::class.java)
            var memo = binding.edittextIncomeplusmemo.text.toString()

            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",16)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)

            startActivity(intent)
        }
        binding.imageviewAccountcategoryCarryover.setOnClickListener{
            var memo = binding.edittextIncomeplusmemo.text.toString()

            val intent = Intent(this,IncomePlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",17)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)

            startActivity(intent)
        }
        binding.imageviewAccountcategoryWithdraw.setOnClickListener{
            var memo = binding.edittextIncomeplusmemo.text.toString()

            val intent = Intent(this,IncomePlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",18)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)

            startActivity(intent)
        }
        binding.imageviewAccountcategoryEtc.setOnClickListener{
            var memo = binding.edittextIncomeplusmemo.text.toString()

            val intent = Intent(this,IncomePlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",19)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)

            startActivity(intent)
        }

        binding.tvMoneyamount.text = moneyAmount.toString()
    }
}