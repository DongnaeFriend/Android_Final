package com.example.dongnaefriend_android.Retrofit2

import android.text.BoringLayout
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
        @SerializedName("transactionCategory") val transactionCategory: String,
        @SerializedName("year") val year: Int,
        @SerializedName("month") val month: Int,
        @SerializedName("day") val day: Int,
        @SerializedName("price") val price: Int,
        @SerializedName("payCategory") val payCategory: Int,
        @SerializedName("memo") val memo: String,
    )
}

data class AccountAllResponse(
    @SerializedName("expenditure") val expenditure : Int,
    @SerializedName("income") val income : Int,
    @SerializedName("budget") val budget : Int,
    @SerializedName("expense") val expense : ArrayList<List>
){
    data class List(
        @SerializedName("transactionCategory") val transactionCategory : String,
        @SerializedName("price") val price : Int,
    )
}

data class PostMemo(
    @SerializedName("memo") val memo : String,
    @SerializedName("done") val done : Boolean,
)

data class MemoResponse(
    @SerializedName("memos") val memos : ArrayList<Memos>
){
    data class Memos(
        @SerializedName("memoId") val memoId : Int,
        @SerializedName("memo") val memo : String,
        @SerializedName("done") val done : Boolean,
        )
}

data class PostPeed(
    @SerializedName("category") val category : Int,
    @SerializedName("title") val title : String,
    @SerializedName("content") val content : String,
    @SerializedName("place") val place : String,
    @SerializedName("placeLocation") val placeLocation : String,
    )


data class PeedResponse(
    @SerializedName("contents") val contents : List<Contents>
){
    data class Contents(
        @SerializedName("id") val id : Long,
        @SerializedName("town") val town : String,
        @SerializedName("category") val category : Int,
        @SerializedName("title") val title : String,
        @SerializedName("content") val content : String,
        @SerializedName("imageUrl") val  imageUrl : String,
        @SerializedName("createdAt") val createdAt : String,
        @SerializedName("view") val view : Int,
        @SerializedName("commentCount") val commentCount : Int,
        @SerializedName("likes") val likes : Int,
    )
}

data class PeedDetailResponse(
    @SerializedName("title") val title : String,
    @SerializedName("content") val content : String,
    @SerializedName("place") val place : String,
    @SerializedName("placeLocation") val placeLocation : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("view") val view : Int,
    @SerializedName("townCertificateCnt") val townCertificateCnt : Int,
    @SerializedName("isWriter") val isWriter : Boolean,
    @SerializedName("likeOrNot") val likeOrNot : Boolean,
    @SerializedName("scarpOrNot") val scarpOrNot : BoringLayout,
    @SerializedName("profileImage") val profileImage : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("category") val category : Int,




    )






