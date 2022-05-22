package com.example.shareTask.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shareTask.databinding.FragmentSettingsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.logout.setOnClickListener{
            Firebase.auth.signOut()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}