package vn.kien.tokoinchallenge.ui.fragment.profile

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.fragment_profile.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.model.User
import vn.kien.tokoinchallenge.ui.base.TokoinFragment
import vn.kien.tokoinchallenge.ui.view.InputDialog

class ProfileFragment : TokoinFragment<ViewDataBinding, ProfileViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.root.apply {
            name_btn.text = viewModel.user?.name ?: getString(R.string.name_request)
            name_btn.setOnClickListener {
                InputDialog(hint = getString(R.string.name_placeholder), defaultValue = viewModel.user?.name) { newName ->
                    viewModel.user = (viewModel.user ?: User(null, null)).apply {
                        val nn = if (newName.isBlank()) {
                            null
                        } else {
                            newName
                        }
                        name = nn
                        name_btn.text = nn ?: getString(R.string.name_request)
                    }
                }.show(childFragmentManager, "input_dialog")
            }
        }

        arrayOf("Bitcoin", "Apple", "Earthquake", "Animal").map {
            val pref = generatePreferenceOption(it, it == viewModel.user?.preference)
            addPreference(pref)
        }
    }

    private fun clearPreferences() {
        val vc = viewBinding.root.preferences_layout.childCount
        for (i in 0 until vc) {
            val v = viewBinding.root.preferences_layout[i]
            if (v is CheckBox) {
                v.isChecked = false
            }
        }
    }

    private fun addPreference(view: View) {
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val commonSpace = viewBinding.root.context.resources.getDimension(R.dimen.common).toInt()
        lp.setMargins(0, 0, commonSpace, commonSpace)
        viewBinding.root.preferences_layout.addView(view, lp)
    }

    private fun generatePreferenceOption(value: String, checked: Boolean = false) = CheckBox(viewBinding.root.context).apply {
        id = View.generateViewId()
        val commonSpace = context.resources.getDimension(R.dimen.common).toInt()
        buttonDrawable = null
        setPadding(commonSpace, commonSpace, commonSpace, commonSpace)
        setTextColor(ContextCompat.getColorStateList(context, R.color.action_text_selector))
        isChecked = checked
        text = value
        background = ContextCompat.getDrawable(context, R.drawable.radio_background)
        setOnCheckedChangeListener(onCheckedChangeListener)
    }

    private val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { v, isChecked ->
        clearPreferences()
        if (v is CheckBox) {
            v.isChecked = isChecked
            viewModel.user = (viewModel.user ?: User(null, null)).apply { preference = if (isChecked) v.text.toString() else null }
        }
    }
}