package vn.kien.tokoinchallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import vn.kien.tokoinchallenge.util.ApiConstants
import vn.kien.tokoinchallenge.util.EndlessRecyclerOnScrollListener
import vn.kien.tokoinchallenge.util.TokoinLogger

abstract class TokoinRecyclerViewModel : TokoinViewModel() {
    protected abstract fun load(page: Int = ApiConstants.initialPage)

    val isShowLoadMore = MutableLiveData(false)
    val isRefreshingData = MutableLiveData(false)
    protected var currentPage: Int = ApiConstants.initialPage

    val onScrollListener = object: EndlessRecyclerOnScrollListener() {
        override fun onLoadMore() {
            isLoading = true
            isShowLoadMore.value = true
            load(++currentPage)
            TokoinLogger.d("loadmore")
        }
    }

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isLoading = true
        isRefreshingData.value = true
        currentPage = ApiConstants.initialPage
        load(currentPage)
    }

    override fun hideLoading() {
        super.hideLoading()
        isRefreshingData.value = false
        isShowLoadMore.value = false
    }

    fun resetLoadMore() {
        onScrollListener.resetOnLoadMore()
    }
}