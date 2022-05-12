package com.example.domain.usecases


interface AuthenticateUserUseCase {
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
    suspend fun createAccount(email:String,password: String, name :String)
}