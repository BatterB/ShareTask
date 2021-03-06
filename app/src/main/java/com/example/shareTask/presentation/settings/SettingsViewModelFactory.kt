package com.example.shareTask.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.AuthenticateUserUseCase
import javax.inject.Inject

class SettingsViewModelFactory @Inject constructor(
    val authenticateUserUseCase: AuthenticateUserUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(authenticateUserUseCase = authenticateUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}