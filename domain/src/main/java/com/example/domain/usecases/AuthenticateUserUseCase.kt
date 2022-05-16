package com.example.domain.usecases


interface AuthenticateUserUseCase {
    suspend fun signIn(email: String, password: String) : Boolean
    suspend fun signOut()
    suspend fun createAccount(email:String,password: String, repeatPassword :String, name :String) : Int
}