package com.example.shareTask.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.TaskModel
import com.example.domain.usecases.AuthenticateUserUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class SettingsViewModel(
    val authenticateUserUseCase : AuthenticateUserUseCase
) : ViewModel() {

    private var _userName = MutableLiveData<String>()
    val userName : LiveData<String>
        get() = _userName

    fun welcomeText() {
        viewModelScope.launch {
            _userName.value = authenticateUserUseCase.getCurrentUser()?.name!!
        }
    }

    fun exit(){
        Firebase.auth.signOut()
        viewModelScope.launch {
            authenticateUserUseCase.signOut()
        }
    }
}