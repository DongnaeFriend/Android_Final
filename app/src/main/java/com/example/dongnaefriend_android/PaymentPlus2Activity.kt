package com.example.dongnaefriend_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dongnaefriend_android.databinding.ActivityPaymentPlus2Binding

private lateinit var binding:ActivityPaymentPlus2Binding
class PaymentPlus2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentPlus2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        //닫기 버튼
        binding.imageviewPaymentclose2.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java)
            startActivity(intent)
        }
        //뒤로가기 버튼
        binding.imageviewPaymentgoback2.setOnClickListener {
            val intent = Intent(this,PaymentPlus1Activity::class.java)
            startActivity(intent)
        }


        //지출내역 선택
        binding.imageviewAccountcategoryFood.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategorySnack.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryMart.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryAlchol.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryShopping.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryHobby.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryHealth.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryLiving.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryFund.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryBeauty.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryTransport.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryTrip.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryEducation.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryBank.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryEtc.setOnClickListener{
            val intent = Intent(this,PaymentPlus3Activity::class.java)
            startActivity(intent)
        }

    }
}