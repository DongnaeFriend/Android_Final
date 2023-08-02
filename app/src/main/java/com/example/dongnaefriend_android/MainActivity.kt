package com.example.dongnaefriend_android

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dongnaefriend_android.databinding.ActivityMainBinding
import com.github.mikephil.charting.BuildConfig
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient


class MainActivity : AppCompatActivity() {
    private lateinit var Binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)


        Binding.ivKakaologin.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=496d0f95e625e90a64a2ffdaaba58120&redirect_uri=http://dongnae.shop/callback"))
            startActivity(intent)



        }

        Binding.btnStart.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)

            startActivity(intent)
        }





        /*
        //https://jdroid.tistory.com/10 참고하여 구현하였음(카카오로그인)
        //토큰 여부 확인
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {

                        kakaoLogin()
                    }
                    else {
                        //기타 에러
                    }
                }
                else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    val intent = Intent(this,HomeActivity::class.java)

                    startActivity(intent)

                }
            }
        }
        else {



        }



        Binding.btnStart.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)

            startActivity(intent)
        }

        Binding.ivKakaologin.setOnClickListener{
            kakaoLogin()

            if (AuthApiClient.instance.hasToken()) {

                val intent = Intent(this,HomeActivity::class.java)

                startActivity(intent)

            }



        }
    }


    //카카오로그인프로세스
    fun kakaoLogin(){
        val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패 $error")
            } else if (token != null) {
                Log.e(TAG, "로그인 성공 ${token.accessToken}")
            }
        }

// 카카오톡 설치 확인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Log.e(TAG, "로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Log.e(TAG, "로그인 성공 ${token.accessToken}")




                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
        }


         */
    }
}