package vn.kien.tokoinchallenge.ui.fragment.detail

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.transition.TransitionInflater
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinFragment

class DetailFragment : TokoinFragment<ViewDataBinding, DetailViewModel>() {

    companion object {
        const val NEWS = "_news_data"
    }

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.news.value = dataTransferHelper.pop(requireArguments().getString(NEWS)!!)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
    }
}