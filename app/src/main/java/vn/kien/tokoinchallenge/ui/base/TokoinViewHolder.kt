package vn.kien.tokoinchallenge.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class TokoinViewHolder<ViewBinding: ViewDataBinding>(open val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)