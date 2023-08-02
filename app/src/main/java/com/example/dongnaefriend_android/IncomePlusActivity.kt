package com.example.dongnaefriend_android

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityIncomePlusBinding
import java.util.Calendar

private lateinit var binding : ActivityIncomePlusBinding
class IncomePlusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomePlusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIncomeplusnext.setOnClickListener {
            val intent = Intent(this, IncomePlus2Activity::class.java)
            startActivity(intent)
        }

        binding.imageviewIncomegoback1.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewIncomeclose1.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }

        binding.tvAccountIncomePlusDate.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            let { it1 ->
                DatePickerDialog( it1, { _, year, month, day ->
                    run {
                        binding.tvAccountIncomePlusDate.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                    }
                }, year, month, day)
            }?.show()
        }


    }
}