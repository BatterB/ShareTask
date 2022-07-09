package com.example.domain.usecases

import com.example.domain.models.UserModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject


class AuthenticateUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    AuthenticateUserUseCase
{
    override suspend fun signIn(email: String, password: String) : Boolean {
       return userRepository.authenticate(email,password)
    }

    override suspend fun getCurrentUser(): UserModel? {
        return userRepository.getCurrentUser()
    }

    override suspend fun signOut() {
        userRepository.clearUser()
    }

    override suspend fun createAccount(email: String, password: String, repeatPassword : String, name: String) : Int {
        return if (password == repeatPassword){
            if(userRepository.createAccount(email, password, name)) {
                1
            }
            else
                0
        }else
            -1

    }

}