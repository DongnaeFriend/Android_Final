package com.example.dongnaefriend_android

import android.app.Application
import com.github.mikephil.charting.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        KakaoSdk.init(this, "fbcc2cec3ee2f053b6b317750ef0538e")
    }
}