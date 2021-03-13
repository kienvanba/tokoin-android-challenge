package vn.kien.tokoinchallenge.ui.fragment.news

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.layout_news_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinFragment
import vn.kien.tokoinchallenge.ui.fragment.detail.DetailFragment
import vn.kien.tokoinchallenge.ui.view.TextAlertDialog
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

        val newsAdapter = NewsAdapter().apply {
            setItemClickListener { news, binding ->
                val bundle = Bundle().apply {
                    putString(DetailFragment.NEWS, dataTransferHelper.push(news))
                }
                binding.root.apply {
                    cover_img.transitionName = "full_cover"
                    title_tv.transitionName = "title"
                    info_layout.transitionName = "info"
                }
                val extras = FragmentNavigatorExtras(
                    binding.root.cover_img to "full_cover",
                    binding.root.title_tv to "title",
                    binding.root.info_layout to "info"
                )
                navigate(R.id.detail_fragment, args = bundle, extras = extras)
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
            itemList.observe(viewLifecycleOwner, {
                newsAdapter.submitList(it)
            })
            errorMessage.observe(viewLifecycleOwner, {
                alert(getString(R.string.error), it, negativeTitle = null)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }
}