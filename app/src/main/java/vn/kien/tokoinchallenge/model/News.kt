package vn.kien.tokoinchallenge.model

import com.google.gson.annotations.SerializedName
import vn.kien.tokoinchallenge.util.appString
import java.util.*

data class News(
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: Date,
    @SerializedName("content") val content: String
) {
    fun getPublishDateString(): String = publishedAt.appString
}

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)