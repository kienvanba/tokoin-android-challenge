package vn.kien.tokoinchallenge.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("preferences") val preferences: List<String>
)