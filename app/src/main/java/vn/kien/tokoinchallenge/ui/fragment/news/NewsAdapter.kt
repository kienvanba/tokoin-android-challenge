package vn.kien.tokoinchallenge.ui.fragment.news

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.layout_news_item.view.*
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.model.News
import vn.kien.tokoinchallenge.ui.base.TokoinRecyclerAdapter

class NewsAdapter : TokoinRecyclerAdapter<News, ViewDataBinding>(object : DiffUtil.ItemCallback<News>() {
    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.source.id == newItem.source.id
    }

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.source.id == newItem.source.id
    }
}) {
    private var listener: ((News, ViewDataBinding) -> Unit)? = null

    override val layoutId: Int
        get() = R.layout.layout_news_item

    override fun bindView(binding: ViewDataBinding, item: News, position: Int) {
        binding.root.setOnClickListener {
            listener?.invoke(item, binding)
        }
    }

    fun setItemClickListener(l : ((News, ViewDataBinding) -> Unit)?) {
        listener = l
    }
}