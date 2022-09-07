package com.example.domain.models

data class UserModel(
    val id: String,
    val name: String? = null,
    val email: String?,
    val tasks: List<String>? = null
)
