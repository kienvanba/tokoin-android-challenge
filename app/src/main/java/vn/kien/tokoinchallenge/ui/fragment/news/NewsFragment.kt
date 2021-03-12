package vn.kien.tokoinchallenge.ui.fragment.news

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_news.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinFragment
import vn.kien.tokoinchallenge.util.TokoinLogger
import vn.kien.tokoinchallenge.util.setupLayout

enum class NewsType {
    Headline, Custom
}

class NewsFragment(private val type: NewsType) : TokoinFragment<ViewDataBinding, NewsViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance(type: NewsType) = NewsFragment(type)
    }

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_news
    override val viewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.type = type
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TokoinLogger.e("init fragment($this) with -> $type")

        val newsAdapter = NewsAdapter().apply {
            setItemClickListener {
                //
            }
        }

        viewBinding.root.apply {
            news_list.apply {
                setupLayout(RecyclerView.VERTICAL, true)
                adapter = newsAdapter
            }
            add_preference_btn.setOnClickListener {
                navigate(R.id.profile_fragment, navOptions = slideInFromRight)
            }
        }

        viewModel.apply {
            itemList.observe(viewLifecycleOwner, Observer {
                TokoinLogger.e("submit list [$type] -> ${it.size}")
                newsAdapter.submitList(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }
}