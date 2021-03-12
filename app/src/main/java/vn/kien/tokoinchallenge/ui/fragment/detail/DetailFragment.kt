package vn.kien.tokoinchallenge.ui.fragment.detail

import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinFragment

class DetailFragment : TokoinFragment<ViewDataBinding, DetailViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModel()
}