package com.example.shareTask.presentation.authentication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.usecases.AuthenticateUserUseCase
import com.example.shareTask.R
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.FragmentLoginBinding
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var authenticateUserUseCase: AuthenticateUserUseCase

    @Inject
    lateinit var authenticationViewModelFactory: AuthenticationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity?.applicationContext as ShareTask).appComponent.inject(this)

        binding = FragmentLoginBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, authenticationViewModelFactory)[LoginViewModel::class.java]

        setObserver()
        setEventListener()

        return binding.root
    }


    private fun setObserver() {
        viewModel.isLoginSuccessful.observe(viewLifecycleOwner) {
            Log.e(TAG,viewModel.isLoginSuccessful.value.toString())
            if (it != null) {
                if (it == true) findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
            }
        }
    }

    private fun setEventListener() {
        binding.login.setOnClickListener {
            viewModel.login(binding.username.text.toString(), binding.password.text.toString())
        }

        binding.registrationButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

}