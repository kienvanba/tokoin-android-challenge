package vn.kien.tokoinchallenge.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class TokoinActivity<ViewBinding: ViewDataBinding, ViewModel: TokoinViewModel> : AppCompatActivity() {
    protected abstract val bindingVariable: Int
    protected abstract val viewModel: ViewModel
    @get:LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var viewBinding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId) as ViewBinding
        viewBinding.apply {
            setVariable(bindingVariable, viewModel)
            root.isClickable = true
            lifecycleOwner = this@TokoinActivity
            executePendingBindings()
        }
    }
}