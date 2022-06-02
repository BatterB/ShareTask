package com.example.shareTask.presentation.authentication

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.usecases.AuthenticateUserUseCase
import com.example.shareTask.R
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.FragmentLoginBinding
import com.example.shareTask.databinding.FragmentRegistrationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private lateinit var viewModel: RegistrationViewModel

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

        binding = FragmentRegistrationBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, authenticationViewModelFactory)[RegistrationViewModel::class.java]

        setObserver()

        setEventListener()

        return binding.root
    }

    private fun setObserver(){
        viewModel.registrationResult.observe(viewLifecycleOwner){
            if (it != null) {
                if (it == 1) {
                    findNavController().navigate(R.id.action_registrationFragment_to_navigation_home)
                    val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
                    bottomNavigation?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setEventListener(){
        binding.registration.setOnClickListener{
            viewModel.registration(
                email = binding.emailField.text.toString().trim(' '),
                password = binding.passwordRegistrationField.text.toString().trim(' '),
                repeatPassword = binding.repeatPasswordRegistrationField.text.toString().trim(' '),
                name = binding.usernameField.text.toString().trim(' ')
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }

}