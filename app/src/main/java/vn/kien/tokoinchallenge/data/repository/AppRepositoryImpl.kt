package vn.kien.tokoinchallenge.data.repository

import io.reactivex.Single
import vn.kien.tokoinchallenge.data.local.AppPref
import vn.kien.tokoinchallenge.data.network.ApiServices
import vn.kien.tokoinchallenge.data.network.Response
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.model.User

class AppRepositoryImpl(private val apiServices: ApiServices, private val appPref: AppPref) : AppRepository {
    override var user: User?
        get() = appPref.user
        set(value) { appPref.user = value}

    //

    override fun getHeadlineList(page: Int, pageSize: Int): Single<Response<News>> {
        return apiServices.getHeadlineList(page, pageSize)
    }

    override fun getNewsList(query: String, page: Int, pageSize: Int): Single<Response<News>> {
        return apiServices.getNewsList(query, page, pageSize)
    }
}