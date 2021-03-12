package vn.kien.tokoinchallenge.ui.activity.main

import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinActivity

class MainActivity : TokoinActivity<ViewDataBinding, MainViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
}