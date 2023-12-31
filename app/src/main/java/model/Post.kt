package model

import java.io.Serializable

data class Post(
    val sequence : Int,
    val cover: Int,
    val title: String,
    val mention: String,
    val like: String,
    val text: String,
    val loc: String,
    val time: String,
    val num: String
) : Serializable

data class PaymentListData(
    val category : String,
    val memo : String,
    val moneyAmount : Int,
)