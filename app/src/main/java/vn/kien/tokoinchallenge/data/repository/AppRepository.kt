package vn.kien.tokoinchallenge.data.repository

import io.reactivex.Single
import vn.kien.tokoinchallenge.data.network.Response
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.model.User
import vn.kien.tokoinchallenge.util.ApiConstants

interface AppRepository {
    var user: User?

    fun getNewsList(
        query: String,
        page: Int = ApiConstants.initialPage,
        pageSize: Int = ApiConstants.defaultPageSize
    ) : Single<Response<News>>
    fun getHeadlineList(
        page: Int = ApiConstants.initialPage,
        pageSize: Int = ApiConstants.defaultPageSize
    ) : Single<Response<News>>
}