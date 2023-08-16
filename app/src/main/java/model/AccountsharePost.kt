package model

import java.io.Serializable

data class AccountsharePost(
    val cover: Int,
    val title: String,
    val mention: String,
    val like: String,
    val text: String,
    val time: String,
    val num: String,
    val categooryimg: Int,
    val categorytext: String
) : Serializable