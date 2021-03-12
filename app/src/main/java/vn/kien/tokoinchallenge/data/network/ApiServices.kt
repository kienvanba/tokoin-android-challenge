package vn.kien.tokoinchallenge.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.util.ApiConstants

interface ApiServices {

    @GET("everything")
    fun getNewsList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : Single<Response<News>>

    @GET("top-headlines?country=us")
    fun getHeadlineList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : Single<Response<News>>
}