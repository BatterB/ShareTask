package com.example.shareTask.presentation.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.AuthenticateUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase) : ViewModel() {

    private val _isLoginSuccessful = MutableLiveData<Boolean?>()
    val isLoginSuccessful : LiveData<Boolean?>
        get() = _isLoginSuccessful

    fun login(email: String, password: String){
        viewModelScope.launch {
            authenticateUserUseCase.signIn(email, password)
        }
    }

}