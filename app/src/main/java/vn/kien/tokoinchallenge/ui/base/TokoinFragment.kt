package vn.kien.tokoinchallenge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import org.koin.android.ext.android.inject
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.data.local.DataTransferHelper
import vn.kien.tokoinchallenge.model.AlertData
import vn.kien.tokoinchallenge.ui.view.TextAlertDialog

abstract class TokoinFragment<ViewBinding: ViewDataBinding, ViewModel: TokoinViewModel> : Fragment() {
    protected abstract val bindingVariable: Int
    @get:LayoutRes
    protected abstract val layoutId: Int
    abstract val viewModel: ViewModel
    protected val dataTransferHelper: DataTransferHelper by inject()
    protected var title: String = TokoinFragment::class.java.simpleName
        set(value) {
            field = value
            (activity as? AppCompatActivity)?.supportActionBar?.title = value
        }

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

    protected fun alert(title: String, message: String, positiveTitle: String = getString(R.string.confirm), negativeTitle: String? = getString(R.string.cancel), listener: TextAlertDialog.TextAlertListener? = null ) {
        val alertData = AlertData(title, message, positiveTitle, negativeTitle)
        val dialog = TextAlertDialog(alertData)
        dialog.setAlertListener(listener)
        dialog.show(childFragmentManager, "text-alert")
    }

    protected val slideInFromRight = navOptions {
        anim {
            enter = R.anim.translate_slide_in_right
            exit = R.anim.translate_slide_out_left
            popEnter = R.anim.translate_slide_in_left
            popExit = R.anim.translate_slide_out_right
        }
    }

    fun navigate(@IdRes resId: Int, @Nullable args: Bundle? = null,
                 @Nullable navOptions: NavOptions? = null, @Nullable extras: FragmentNavigator.Extras? = null
    ) {
        findNavController().navigate(resId, args, navOptions, extras)
    }

    fun navigate(@IdRes resId: Int, @Nullable args: Bundle? = null,
                 @Nullable navOptions: NavOptions? = null
    ) {
        findNavController().navigate(resId, args, navOptions)
    }

    fun navigate(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    override fun onDestroy() {
        viewModel.onViewDestroyed()
        super.onDestroy()
    }
}