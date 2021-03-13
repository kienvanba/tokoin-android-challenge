package vn.kien.tokoinchallenge.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_alert.*
import vn.kien.tokoinchallenge.BR
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.model.AlertData

class TextAlertDialog(private val data: AlertData) : DialogFragment() {
    interface TextAlertListener {
        fun onPositiveClick(dialog: TextAlertDialog)
        fun onNegativeClick(dialog: TextAlertDialog) = Unit
    }

    private lateinit var viewBinding: ViewDataBinding
    private var listener: TextAlertListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_alert, container, false)
        viewBinding.setVariable(BR.data, data)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.root.apply {
            negative_btn.setOnClickListener {
                listener?.onNegativeClick(this@TextAlertDialog)
                dismiss()
            }
            positive_btn.setOnClickListener {
                listener?.onPositiveClick(this@TextAlertDialog)
                dismiss()
            }
        }
    }

    override fun onResume() {
        val lp = dialog?.window?.attributes
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        lp?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = lp
        super.onResume()
    }

    fun setAlertListener(listener: TextAlertListener?) {
        this.listener = listener
    }
}