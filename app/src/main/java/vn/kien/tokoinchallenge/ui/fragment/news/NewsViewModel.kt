package vn.kien.tokoinchallenge.ui.fragment.news

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.kien.tokoinchallenge.data.repository.AppRepository
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.ui.base.TokoinRecyclerViewModel
import vn.kien.tokoinchallenge.util.TokoinLogger

class NewsViewModel(private val appRepository: AppRepository) : TokoinRecyclerViewModel<News>() {
    lateinit var type: NewsType
    val preferenceAvailable = MutableLiveData(true)

    override fun load(page: Int) {
        when (type) {
            NewsType.Headline -> { loadHeadlines(page) }
            NewsType.Custom -> { loadCustoms(page) }
        }
        TokoinLogger.e("load data -> $type")
    }

    private fun loadCustoms(page: Int) {
        preferenceAvailable.value = !appRepository.user?.preference.isNullOrEmpty()
        if (preferenceAvailable.value == true) {
            val query = appRepository.user?.preference ?: return
            showLoading()
            addDisposable(appRepository.getNewsList(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onLoadSuccess(it)
                }, {
                    onLoadFail(it)
                }))
        } else {
            hideLoading()
        }
    }

    private fun loadHeadlines(page: Int) {
        showLoading()
        addDisposable(appRepository.getHeadlineList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onLoadSuccess(it)
            }, {
                onLoadFail(it)
            }))
    }
}