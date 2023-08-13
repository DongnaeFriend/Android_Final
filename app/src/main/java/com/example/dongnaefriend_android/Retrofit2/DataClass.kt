package com.example.dongnaefriend_android.Retrofit2

import com.google.gson.annotations.SerializedName

data class BudgetResponse(
    @SerializedName("budget") val budget:Int,
)

data class PostBudget(
    @SerializedName("year") val year:Int,
    @SerializedName("month") val month:Int,
    @SerializedName("amount") val amount:Int,
)