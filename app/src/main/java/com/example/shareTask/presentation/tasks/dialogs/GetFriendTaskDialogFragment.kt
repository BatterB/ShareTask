package com.example.shareTask.presentation.tasks.dialogs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.shareTask.databinding.DialogFragmentGetFriendTaskBinding
import com.example.shareTask.databinding.DialogFragmentShareWithFriendBinding

class GetFriendTaskDialogFragment: DialogFragment(){

    private var _binding: DialogFragmentGetFriendTaskBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogFragmentGetFriendTaskBinding.inflate(layoutInflater)

        setEventListener()

        return binding.root
    }

    private fun setEventListener(){
        binding.buttonCancel.setOnClickListener{
            dialog?.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}