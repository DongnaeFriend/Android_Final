package com.example.dongnaefriend_android

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.dongnaefriend_android.databinding.ActivityBasicSettingBinding

private lateinit var binding:ActivityBasicSettingBinding

class BasicSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //정보를 입력하면 다음화면(HomeActivity)으로
        binding.btnBasicSettingNext.setOnClickListener {
            if (true) {
                val intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
            }


        }
    }

}