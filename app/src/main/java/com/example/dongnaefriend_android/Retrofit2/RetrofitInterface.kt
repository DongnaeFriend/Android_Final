package com.example.dongnaefriend_android.Retrofit2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("account/budget")
    fun getBudget(
        @Query("year") year: Int ,
        @Query("month") month:Int,
        @Header("Authorization") authToken: String
    ): Call<BudgetResponse>
}