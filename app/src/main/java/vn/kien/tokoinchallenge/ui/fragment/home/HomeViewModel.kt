package vn.kien.tokoinchallenge.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.kien.tokoinchallenge.data.repository.AppRepository
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.ui.base.TokoinRecyclerViewModel
import vn.kien.tokoinchallenge.ui.base.TokoinViewModel
import vn.kien.tokoinchallenge.util.ApiConstants

class HomeViewModel(private val appRepository: AppRepository) : TokoinRecyclerViewModel() {
    val newsList = MutableLiveData<List<News>>()

    fun loadHeadlines() {
        showLoading()
        load()
    }

    override fun load(page: Int) {
        addDisposable(appRepository.getHeadlineList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hideLoading()
                if (it.articles.isEmpty() && currentPage != ApiConstants.initialPage) currentPage--
                if (currentPage == ApiConstants.initialPage) {
                    newsList.value = it.articles
                    resetLoadMore()
                } else {
                    val arr = newsList.value?.toMutableList()
                    arr?.addAll(it.articles)
                    newsList.value = arr
                }
            }, {
                currentPage--
                onLoadFail(it)
            }))
    }
}