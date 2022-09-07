package com.example.domain.usecases

import com.example.domain.models.UserModel


interface AuthenticateUserUseCase {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun getCurrentUser(): UserModel?
    suspend fun signOut()
    suspend fun createAccount(
        email: String,
        password: String,
        repeatPassword: String,
        name: String
    ): Int
}