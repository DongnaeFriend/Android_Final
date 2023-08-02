package com.example.dongnaefriend_android

import com.google.gson.annotations.SerializedName

data class BudgetResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("httpStatus") val httpStatus: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Data>
) {
    //
    data class Data(
        @SerializedName("budget") val budget: Int,
    )
}
