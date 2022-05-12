package com.example.domain.usecases

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class AuthenticateUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    AuthenticateUserUseCase
{
    override suspend fun signIn(email: String, password: String) {
        userRepository.authenticate(email,password)
    }

    override suspend fun signOut() {
        userRepository.clearUser()
    }

    override suspend fun createAccount(email: String, password: String, name: String) {
        userRepository.createAccount(email, password, name)
    }

}