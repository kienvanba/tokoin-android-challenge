package vn.kien.tokoinchallenge.data.network

import com.google.gson.annotations.SerializedName

data class Response<Model>(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Model>
)