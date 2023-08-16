package com.example.dongnaefriend_android

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dongnaefriend_android.databinding.ActivityBasicSettingBinding
import com.example.dongnaefriend_android.databinding.ActivityHomeBinding
import com.kakao.sdk.user.UserApiClient

// 나랑 푸시 확인용 주석 //브랜치생성
class HomeActivity : AppCompatActivity() {
    private lateinit var Binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(Binding.root)


        //첫 로그인시(닉네임/사진 값 없을시)BasicSettingActivity띄우기
        if(true){
            val intent = Intent(this, BasicSettingActivity::class.java)

            //startActivity(intent)
        }





        //seleteced item id(바텀네비게이션 초기선택값 결정)변경 위한 함수
        var category_1 = R.id.menu_home//if문이 돌아가지 않으면 초기값 home

        //if문이 돌아가면, 시작값이  home이 되지 않음
        while(true) {
            //AccountbookActivity에서 선택한 menu에 따른 값을 받음
            val category = intent.getIntExtra("text", 0)

            if (category == 2) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(Binding.containerFragment.id, CommunityFragment())
                    .commitAllowingStateLoss()

                break

            } else if (category == 3) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(Binding.containerFragment.id, DongnaeHomeFragment())
                    .commitAllowingStateLoss()
                category_1 = R.id.menu_dongnaeinformation
                break

            } else if (category == 5) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(Binding.containerFragment.id, MypageFragment())
                    .commitAllowingStateLoss()
                category_1 = R.id.menu_mypage
                break
            }

            //if문이 돌아가지 않으면, 초기값 home
            supportFragmentManager
                .beginTransaction()
                .replace(Binding.containerFragment.id, HomeFragment())
                .commitAllowingStateLoss()
            break
        }
        Binding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(Binding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }



                    R.id.menu_dongnaeinformation -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(Binding.containerFragment.id, DongnaeInformationFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_accountbook -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(Binding.containerFragment.id, AccountbookFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(Binding.containerFragment.id, MypageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = category_1

        }



    }
}