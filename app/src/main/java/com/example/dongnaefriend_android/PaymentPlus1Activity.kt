package com.example.dongnaefriend_android

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityPaymentPlus1Binding
import java.util.Calendar

class PaymentPlus1Activity : AppCompatActivity() {

    private lateinit var binding:ActivityPaymentPlus1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentPlus1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonPaymentplusnext.setOnClickListener {

            val intent = Intent(this, PaymentPlus2Activity::class.java)
            startActivity(intent)
        }

        binding.imageviewPaymentgoback1.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewPaymentclose1.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }

        binding.tvAccountPaymentPlusDate.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            let { it1 ->
                DatePickerDialog( it1, { _, year, month, day ->
                    run {
                        binding.tvAccountPaymentPlusDate.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                    }
                }, year, month, day)
            }?.show()
        }

    }
}