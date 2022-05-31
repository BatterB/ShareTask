package com.example.shareTask.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shareTask.R
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.FragmentSettingsBinding
import com.example.shareTask.presentation.tasks.TasksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel : SettingsViewModel

    @Inject
    lateinit var settingsViewModelFactory : SettingsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.applicationContext as ShareTask).appComponent.inject(this)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this,settingsViewModelFactory)[SettingsViewModel::class.java]

        setEventListener()

        return binding.root
    }

    private fun setEventListener(){
        binding.logout.setOnClickListener{
            viewModel.exit()
            findNavController().navigate(R.id.loginFragment)
            val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNavigation?.visibility = View.GONE
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}