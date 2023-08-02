package com.example.dongnaefriend_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityPaymentPlus3Binding

private lateinit var binding:ActivityPaymentPlus3Binding
class PaymentPlus3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentPlus3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageviewPaymentclose3.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }

        binding.imageviewPaymentgoback3.setOnClickListener {
            val intent = Intent(this,PaymentPlus2Activity::class.java)
            startActivity(intent)
        }

        binding.imageviewPaymentmethodCash.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewPaymentmethodCheckcard.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewPaymentmethodCrditcard.setOnClickListener {
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
    }
}