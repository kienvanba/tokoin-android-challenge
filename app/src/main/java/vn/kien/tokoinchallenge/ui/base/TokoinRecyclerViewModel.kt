package vn.kien.tokoinchallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import vn.kien.tokoinchallenge.data.network.Response
import vn.kien.tokoinchallenge.util.ApiConstants
import vn.kien.tokoinchallenge.util.EndlessRecyclerOnScrollListener
import vn.kien.tokoinchallenge.util.TokoinLogger

abstract class TokoinRecyclerViewModel<Item> : TokoinViewModel() {
    abstract fun load(page: Int = ApiConstants.initialPage)

    val isShowLoadMore = MutableLiveData(false)
    val isRefreshingData = MutableLiveData(false)
    val itemList = MutableLiveData<List<Item>>()
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

    fun onLoadSuccess(data: Response<Item>) {
        if (data.items.isEmpty() && currentPage != ApiConstants.initialPage) currentPage--
        if (currentPage == ApiConstants.initialPage) {
            itemList.value = data.items
            onScrollListener.resetOnLoadMore()
        } else {
            val arr = itemList.value?.toMutableList()
            arr?.addAll(data.items)
            itemList.value = arr
        }
        hideLoading()
    }

    override fun onLoadFail(throwable: Throwable) {
        super.onLoadFail(throwable)
        if (currentPage != ApiConstants.initialPage) currentPage--
    }
}