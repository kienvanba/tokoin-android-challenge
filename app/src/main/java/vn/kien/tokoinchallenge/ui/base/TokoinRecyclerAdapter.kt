package vn.kien.tokoinchallenge.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import vn.kien.tokoinchallenge.BR
import java.util.concurrent.Executors

abstract class TokoinRecyclerAdapter<Item, ViewBinding: ViewDataBinding>(callback: DiffUtil.ItemCallback<Item>)
    : ListAdapter<Item, TokoinViewHolder<ViewBinding>>(
    AsyncDifferConfig.Builder<Item>(callback).setBackgroundThreadExecutor(
        Executors.newSingleThreadExecutor()).build()) {

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokoinViewHolder<ViewBinding> {
        return TokoinViewHolder(
            DataBindingUtil.inflate<ViewBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        ).apply { bindFirstTime(this)})
    }

    override fun onBindViewHolder(holder: TokoinViewHolder<ViewBinding>, position: Int) {
        try {
            val item: Item = getItem(position)
            holder.binding.setVariable(BR.item, item)
            bindView(holder.binding, item, position)
        } catch (e: IndexOutOfBoundsException) {
            bind(holder.binding, position)
        }
        holder.binding.executePendingBindings()
    }

    protected open fun bindFirstTime(binding: ViewBinding) {}

    /**
     * override if need
     * bind view
     */
    protected open fun bindView(binding: ViewBinding, item: Item, position: Int) {}

    protected open fun bind(binding: ViewBinding, position: Int) {}
}