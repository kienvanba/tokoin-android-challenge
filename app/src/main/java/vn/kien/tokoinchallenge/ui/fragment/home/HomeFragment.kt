package vn.kien.tokoinchallenge.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinFragment
import vn.kien.tokoinchallenge.util.setupLayout

class HomeFragment : TokoinFragment<ViewDataBinding, HomeViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }

        viewModel.apply {
            loadHeadlines()
            newsList.observe(viewLifecycleOwner, Observer {
                newsAdapter.submitList(it)
            })
        }
    }
}