package vn.kien.tokoinchallenge.ui.fragment.detail

import androidx.lifecycle.MutableLiveData
import vn.kien.tokoinchallenge.data.repository.AppRepository
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.ui.base.TokoinViewModel

class DetailViewModel(private val appRepository: AppRepository) : TokoinViewModel() {
    val news = MutableLiveData<News>()
}