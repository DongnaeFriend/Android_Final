package com.example.dongnaefriend_android.Retrofit2

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object RetrofitClient {
    private var instance: Retrofit? = null
    private const val CONNECT_TIMEOUT_SEC = 20000L

    fun getInstance() : Retrofit {
        if(instance == null){

            // 로깅인터셉터 세팅
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            // OKHttpClient에 로깅인터셉터 등록
            /*val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()
             */
            //사이트 인증서 (TrustOkHttpClientUtil) 불러오는 코드
            val client = TrustOkHttpClientUtil.getUnsafeOkHttpClient().build()



            instance = Retrofit.Builder()
                .baseUrl("https://dongnae.shop/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) // Retrofit 객체에 OkHttpClient 적용
                .build()
        }
        return instance!!
    }

}