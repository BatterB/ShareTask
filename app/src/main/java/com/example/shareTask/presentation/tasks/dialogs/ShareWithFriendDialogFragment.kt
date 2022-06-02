package com.example.shareTask.presentation.tasks.dialogs

import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.DialogFragment
import com.example.shareTask.databinding.DialogFragmentShareWithFriendBinding

class ShareWithFriendDialogFragment(private val code : String) : DialogFragment(){

    private var _binding: DialogFragmentShareWithFriendBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogFragmentShareWithFriendBinding.inflate(layoutInflater)

        binding.shareCode.text = code

        setEventListener()

        return binding.root
    }

    private fun setEventListener(){
        binding.buttonOk.setOnClickListener{
            dialog?.dismiss()
        }

        binding.copyCode.setOnClickListener{
            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Code", binding.shareCode.text.toString())
            clipboard.setPrimaryClip(clip)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}