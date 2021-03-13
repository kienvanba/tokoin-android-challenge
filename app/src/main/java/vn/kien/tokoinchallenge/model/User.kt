package vn.kien.tokoinchallenge.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") var name: String?,
    @SerializedName("preference") var preference: String?
)