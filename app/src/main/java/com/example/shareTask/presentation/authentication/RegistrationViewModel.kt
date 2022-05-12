package com.example.shareTask.presentation.authentication

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.AuthenticateUserUseCase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase): ViewModel() {

}