package vn.kien.tokoinchallenge.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("preference") val preference: String
)