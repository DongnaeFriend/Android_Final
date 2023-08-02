package com.example.dongnaefriend_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityIncomePlus3Binding
import com.example.dongnaefriend_android.databinding.ActivityPaymentPlus3Binding

private lateinit var binding:ActivityIncomePlus3Binding

class IncomePlus3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomePlus3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageviewIncomeclose3.setOnClickListener {
            val intent = Intent(this,AccountbookActivity::class.java)
            startActivity(intent)
        }

        binding.imageviewIncomegoback3.setOnClickListener {
            val intent = Intent(this,IncomePlus2Activity::class.java)
            startActivity(intent)
        }

        binding.imageviewIncomemethodCash.setOnClickListener {
            val intent = Intent(this,AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewIncomemethodCheckcard.setOnClickListener {
            val intent = Intent(this,AccountbookActivity::class.java)
            startActivity(intent)
        }
        binding.imageviewIncomemethodCrditcard.setOnClickListener {
            val intent = Intent(this,AccountbookActivity::class.java)
            startActivity(intent)
        }
    }
}