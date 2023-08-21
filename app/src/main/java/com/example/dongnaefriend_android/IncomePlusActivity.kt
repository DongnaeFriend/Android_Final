package com.example.dongnaefriend_android

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityIncomePlusBinding
import java.util.Calendar

private lateinit var binding : ActivityIncomePlusBinding
class IncomePlusActivity : AppCompatActivity() {

    var Year : Int = 0
    var Month : Int = 0
    var Day : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = Intent(this, IncomePlus2Activity::class.java)



        binding = ActivityIncomePlusBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.imageviewIncomegoback1.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewIncomeclose1.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.tvAccountIncomePlusDate.setText(year.toString() + "년 " + (month + 1).toString() + "월 " + day.toString() + "일")
        Year = year
        Month = month
        Day = day
        intent.putExtra("year",Year)
        intent.putExtra("month",Month+1)
        intent.putExtra("day",Day)

        binding.tvAccountIncomePlusDate.setOnClickListener {

            let { it1 ->
                DatePickerDialog( it1, { _, year, month, day ->
                    run {
                        binding.tvAccountIncomePlusDate.setText(year.toString() + "년 " + (month + 1).toString() + "월 " + day.toString() + "일")
                    }

                    Year = year
                    Month = month
                    Day = day
                    intent.putExtra("year",Year)
                    intent.putExtra("month",Month+1)
                    intent.putExtra("day",Day)
                }, year, month, day)
            }?.show()
        }

        binding.buttonIncomeplusnext.setOnClickListener {

            val MoneyAmount = binding.edittextIncomeplus.text.toString()
            val moneyAmount = MoneyAmount.toInt()

            intent.putExtra("text",moneyAmount)
            startActivity(intent)
        }


    }
}