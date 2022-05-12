package com.example.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.data.dao.UserDao
import com.example.domain.models.UserModel
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val userDao : UserDao) : UserRepository {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String,name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(email: String, password: String): Boolean {

        var isSuccess = false

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    isSuccess = true
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        return isSuccess
    }

    override fun getCurrentUser(): UserModel? {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserProfile(userId: String, name: String, email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearUser() {
        TODO("Not yet implemented")
    }


}