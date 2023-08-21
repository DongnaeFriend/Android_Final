package com.example.dongnaefriend_android.Retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface RetrofitInterfaceTommy {

    //예산조회
    @GET("api/account/budget")
    fun getBudget(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Header("Authorization") authToken: String
    ): Call<BudgetResponse>


    //예산 설정
    @POST("api/account/budget")
    fun postBudget(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("amount") amount: Int,
        @Body request: PostBudget,
        @Header("Authorization") authToken: String
    ): Call<Void>


    @POST("api/account")
    fun postMoneyHistory(
        @Body request : PostMoneyHistory,
        @Header("Authorization") authToken: String
    ):Call<Void>

    @GET("api/account")
    fun getMoneyHistory(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day : Int,
        @Header("Authorization") authToken: String
    ): Call<MoneyHistoryResponse>

    @GET("api/account/all")
    fun getAccountAll(
        @Query("year") year:Int,
        @Query("month") month:Int,
        @Header("Authorization") authToken: String
    ): Call<AccountAllResponse>

    @POST("api/account/memo")
    fun postAccountMemo(
        @Query("year") year:Int,
        @Query("month") month:Int,
        @Body request : PostMemo,
        @Header("Authorization") authToken: String
    ):Call<Void>

    @GET("api/account/memo")
    fun getMemo(
        @Query("year") year:Int,
        @Query("month") month:Int,
        @Header("Authorization") authToken: String
    ):Call<MemoResponse>

    @PUT("api/account/memo")
    fun putMemo(
        @Query("id") id : Int,
        @Body request : PostMemo,
        @Header("Authorization") authToken: String
    ):Call<Void>
}

