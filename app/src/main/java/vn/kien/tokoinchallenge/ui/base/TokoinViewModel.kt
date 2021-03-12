package vn.kien.tokoinchallenge.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class TokoinViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun onActivityDestroyed() {
        compositeDisposable.clear()
    }
}