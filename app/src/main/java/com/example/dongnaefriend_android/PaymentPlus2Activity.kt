package com.example.dongnaefriend_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dongnaefriend_android.databinding.ActivityPaymentPlus2Binding

private lateinit var binding:ActivityPaymentPlus2Binding

class PaymentPlus2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentPlus2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var moneyAmount = intent.getIntExtra("text", 0)
        var year = intent.getIntExtra("year",100).toInt()
        var month = intent.getIntExtra("month",100).toInt()
        var day = intent.getIntExtra("day",100).toInt()

        var memo = binding.edittextPaymentplusmemo.text.toString()





        //닫기 버튼
        binding.imageviewPaymentclose2.setOnClickListener{
            val intent = Intent(this, AccountbookActivity::class.java )
            startActivity(intent)
        }
        //뒤로가기 버튼
        binding.imageviewPaymentgoback2.setOnClickListener {
            val intent = Intent(this,PaymentPlus1Activity::class.java)
            startActivity(intent)
        }


        //지출내역 선택
        binding.imageviewAccountcategoryFood.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",0)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)

            startActivity(intent)
        }
        binding.imageviewAccountcategorySnack.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",1)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryMart.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",2)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryAlchol.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",3)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryShopping.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",4)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryHobby.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",5)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryHealth.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",6)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryLiving.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",7)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryFund.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",8)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryBeauty.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",9)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryTransport.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",10)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryTrip.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",11)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryEducation.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",12)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryBank.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",13)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }
        binding.imageviewAccountcategoryEtc.setOnClickListener{
            var memo = binding.edittextPaymentplusmemo.text.toString()

            val intent = Intent(this,PaymentPlus3Activity::class.java)
            intent.putExtra("moneyAmount",moneyAmount)
            intent.putExtra("category",14)
            intent.putExtra("year",year)
            intent.putExtra("month",month)
            intent.putExtra("day",day)
            intent.putExtra("memo",memo)
            startActivity(intent)
        }



        binding.tvMoneyamount.text = moneyAmount.toString()

    }
}