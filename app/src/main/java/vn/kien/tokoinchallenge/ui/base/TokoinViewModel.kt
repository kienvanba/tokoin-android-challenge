package vn.kien.tokoinchallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class TokoinViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    protected var isLoading = false

    val isShowLoading = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()

    protected open fun onLoadFail(throwable: Throwable) {
        try {
            when (throwable) {
                is UnknownHostException, is ConnectException -> {
                    errorMessage.value = "No Internet Connection"
                }
                is SocketTimeoutException -> {
                    errorMessage.value = "Connect timeout, please retry"
                }
                else -> {
                    errorMessage.value = throwable.message
                }
            }
        } catch (e: Exception) {
            errorMessage.value = throwable.message
        }
        hideLoading()
    }

    open fun showLoading() {
        if (!isLoading) {
            isLoading = true
            isShowLoading.value = true
        }
    }

    open fun hideLoading() {
        if (isLoading) {
            isLoading = false
            isShowLoading.value = false
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun onViewDestroyed() {
        compositeDisposable.clear()
    }
}