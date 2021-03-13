package vn.kien.tokoinchallenge.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.ui.base.TokoinFragment
import vn.kien.tokoinchallenge.ui.fragment.webview.WebViewFragment

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
        val news : News = dataTransferHelper.pop(requireArguments().getString(NEWS)!!)
        title = news.source.name
        viewModel.news.value = news
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.root.apply {
            full_post_btn.setOnClickListener {
                val bundle = Bundle().apply {
                    putString(WebViewFragment.URL, dataTransferHelper.push(viewModel.news.value!!))
                }
                navigate(R.id.web_view_fragment, args = bundle, navOptions = slideInFromRight)
            }
        }
    }
}