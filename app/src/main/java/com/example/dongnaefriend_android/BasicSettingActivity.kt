package com.example.dongnaefriend_android

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.fitCenter
import com.bumptech.glide.request.RequestOptions
import com.example.dongnaefriend_android.databinding.ActivityBasicSettingBinding
import java.io.File
import java.lang.System.load

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


        //프로필 이미지 설정 https://aries574.tistory.com/452 참고
        binding.ivBasicSettingPhoto.setOnClickListener{

            val intent = Intent(Intent.ACTION_PICK)
            intent.type  = "image/*"
            activityResult.launch(intent)
        }

    }

    private val activityResult:ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode == RESULT_OK && it.data != null){
            val uri = it.data!!.data

            Glide.with(this)
                .load(uri)
                .into(binding.ivBasicSettingPhoto)
        }
    }


}