package com.example.domain.repository

import com.example.domain.models.UserModel

interface UserRepository {
    suspend fun createAccount(email: String, password: String,name: String): Boolean
    suspend fun authenticate(email: String, password: String): Boolean
    suspend fun getCurrentUser(): UserModel?
    suspend fun updateUserProfile(userId: String, name: String, email: String)
    suspend fun clearUser()
}