package com.example.dongnaefriend_android.Retrofit2

import androidx.browser.browseractions.BrowserActionsIntent.BrowserActionsItemId
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

data class PostMoneyHistory(
    @SerializedName("type") val type : Int,
    @SerializedName("transactionCategory") val transactionCategory : Int,
    @SerializedName("year") val year : Int,
    @SerializedName("month") val month : Int,
    @SerializedName("day") val day : Int,
    @SerializedName("price") val price : Int,
    @SerializedName("payCategory") val payCategory : Int,
    @SerializedName("categoryMemo") val CategoryMemo : String,
)

data class MoneyHistoryResponse(
    @SerializedName("transactions") val transactions : ArrayList<HistoryList>
)
{
    data class HistoryList(
        @SerializedName("id") val id: Int,
        @SerializedName("type") val type: Int,
        @SerializedName("transactionCategory") val transactionCategory: Int,
        @SerializedName("year") val year: Int,
        @SerializedName("month") val month: Int,
        @SerializedName("day") val day: Int,
        @SerializedName("price") val price: Int,
        @SerializedName("payCategory") val payCategory: Int,
        @SerializedName("memo") val memo: String,
    )
}



