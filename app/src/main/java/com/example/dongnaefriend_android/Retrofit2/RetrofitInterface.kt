package com.example.dongnaefriend_android.Retrofit2

import com.kakao.sdk.user.model.User
import model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {

    //예산조회
    @GET("api/account/budget")
    fun getBudget(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Header("Authorization") authToken: String
    ): Call<BudgetResponse>


    //예산 설정
    @POST("account/budget")
    fun postBudget(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("amount") amount: Int,
        @Body request: PostBudget,
        @Header("Authorization") authToken: String
    ): Call<Void>
}

