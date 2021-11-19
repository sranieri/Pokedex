package com.sample.pokedex.presentation.ui.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.sample.pokedex.databinding.FragmentTitleDialogBinding

class TitleDialogFragment : DialogFragment() {

    lateinit var binding: FragmentTitleDialogBinding

    private var mPositiveButton: String? = null
    private var mPositiveButtonListener: ((DialogFragment) -> Unit)? = null
    private var mNegativeButton: String? = null
    private var mNegativeButtonListener: ((DialogFragment) -> Unit)? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTitleDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_TITLE)?.let { label ->
            binding.title.text = label
        } ?: run { binding.title.isVisible = false }

        mNegativeButton?.let { text ->
            binding.negativeButton.text = text
            binding.negativeButton.setOnClickListener {
                mNegativeButtonListener?.invoke(this)
            }
        } ?: run { binding.negativeButton.isVisible = false }

        mPositiveButton?.let { text ->
            binding.positiveButton.text = text
            binding.positiveButton.setOnClickListener {
                mPositiveButtonListener?.invoke(this)
            }
        } ?: run { binding.positiveButton.isVisible = false }

    }

    fun setPositiveButton(
        text: String,
        listener: ((DialogFragment) -> Unit)? = null
    ): TitleDialogFragment {
        mPositiveButton = text
        mPositiveButtonListener = listener
        return this
    }

    fun setNegativeButton(
        text: String?,
        listener: ((DialogFragment) -> Unit)? = null
    ): TitleDialogFragment {
        mNegativeButton = text
        mNegativeButtonListener = listener
        return this
    }

    fun showDialog(fragmentManager: FragmentManager) {
        if (!isAdded && !fragmentManager.isStateSaved) {
            showNow(fragmentManager, TAG)
        }
    }

    fun hideDialog() {
        if (isAdded) {
            dismissAllowingStateLoss()
        }
    }

    companion object {
        const val TAG = "TitleDialogFragment"
        private const val ARG_TITLE = "arg_title"

        @JvmStatic
        fun newInstance(
            title: String? = null
        ) =
            TitleDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }
    }

}