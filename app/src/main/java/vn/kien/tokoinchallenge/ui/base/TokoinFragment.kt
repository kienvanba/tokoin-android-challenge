package vn.kien.tokoinchallenge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class TokoinFragment<ViewBinding: ViewDataBinding, ViewModel: TokoinViewModel> : Fragment() {
    protected abstract val bindingVariable: Int
    @get:LayoutRes
    protected abstract val layoutId: Int
    abstract val viewModel: ViewModel

    protected lateinit var viewBinding: ViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            setVariable(bindingVariable, viewModel)
            root.isClickable = true
            lifecycleOwner = this@TokoinFragment
            executePendingBindings()
        }
    }
}