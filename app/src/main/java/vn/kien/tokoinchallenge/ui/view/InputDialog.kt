package vn.kien.tokoinchallenge.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_input.view.*
import vn.kien.tokoinchallenge.R

class InputDialog(private val hint: String, private val defaultValue: String? = null, private val onClose: (String) -> Unit) : DialogFragment() {

    private lateinit var viewBinding: ViewDataBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_input, null, false)
        builder.setView(viewBinding.root)
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return viewBinding.root.apply {
            input.hint = hint
            input.editText?.setText(defaultValue)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.root.apply {
            confirm_btn.setOnClickListener {
                onClose.invoke(input.editText?.text.toString())
                dismiss()
            }
        }
    }
}