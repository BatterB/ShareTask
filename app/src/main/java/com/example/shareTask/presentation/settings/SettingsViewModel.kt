package com.example.shareTask.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.AuthenticateUserUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class SettingsViewModel(
    val authenticateUserUseCase : AuthenticateUserUseCase
) : ViewModel() {

    fun exit(){
        Firebase.auth.signOut()
        viewModelScope.launch {
            authenticateUserUseCase.signOut()
        }
    }
}