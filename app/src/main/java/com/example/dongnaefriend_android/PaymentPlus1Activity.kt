package com.example.dongnaefriend_android

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityPaymentPlus1Binding
import java.util.Calendar

class PaymentPlus1Activity : AppCompatActivity() {

    var Year : Int = 0
    var Month : Int = 0
    var Day : Int = 0

    private lateinit var binding:ActivityPaymentPlus1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = Intent(this, PaymentPlus2Activity::class.java)


        binding = ActivityPaymentPlus1Binding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.imageviewPaymentgoback1.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewPaymentclose1.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        binding.tvAccountPaymentPlusDate.setText(year.toString() + "년 " + (month + 1).toString() + "월 " + day.toString() + "일")
        Year = year
        Month = month
        Day = day
        intent.putExtra("year",Year)
        intent.putExtra("month",Month+1)
        intent.putExtra("day",Day)

        binding.tvAccountPaymentPlusDate.setOnClickListener {

            let { it1 ->
                DatePickerDialog( it1, { _, year, month, day ->
                    run {
                        binding.tvAccountPaymentPlusDate.setText(year.toString() + "년 " + (month + 1).toString() + "월 " + day.toString() + "일")
                    }
                    Year = year
                    Month = month
                    Day = day
                    intent.putExtra("year",Year)
                    intent.putExtra("month",Month+1)
                    intent.putExtra("day",Day)

                                       }, year, month, day
                )

            }?.show()

        }

        binding.buttonPaymentplusnext.setOnClickListener {

            val MoneyAmount = binding.edittextPaymentplus.text.toString()
            val moneyAmount = MoneyAmount.toInt()


            intent.putExtra("text",moneyAmount)
            startActivity(intent)


        }

    }
}