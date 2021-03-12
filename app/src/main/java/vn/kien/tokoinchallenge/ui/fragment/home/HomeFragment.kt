package vn.kien.tokoinchallenge.ui.fragment.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.ui.base.TokoinFragment
import vn.kien.tokoinchallenge.ui.fragment.news.NewsAdapter
import vn.kien.tokoinchallenge.util.setupLayout

class HomeFragment : TokoinFragment<ViewDataBinding, HomeViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewBinding.root.apply {
            pager.adapter = HomePagerAdapter(this@HomeFragment)
            TabLayoutMediator(tab_layout, pager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.headline)
                    1 -> getString(R.string.custom)
                    else -> "UnImplemented tab title"
                }
            }.attach()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
    }
}