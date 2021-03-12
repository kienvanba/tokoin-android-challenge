package vn.kien.tokoinchallenge.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("author") val author: String
)