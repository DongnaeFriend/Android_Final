package com.example.dongnaefriend_android.Retrofit2

import com.google.gson.annotations.SerializedName


//가계부 예산 조회 get
data class BudgetResponse(
    @SerializedName("budget") val budget:Int,
)


//가계부 예산 설정 post
data class PostBudget(
    @SerializedName("year") val year:Int,
    @SerializedName("month") val month:Int,
    @SerializedName("amount") val amount:Int,
)

data class moneyHistory(
    var type : Int = 1,
    var category : Int = 4,
    var year : Int = 2023,
    var month : Int = 7,
    var day : Int = 15,
    var price : Int = 15000,
    var pay : Int = 2,
    var memo : String = "타코야끼"
)

