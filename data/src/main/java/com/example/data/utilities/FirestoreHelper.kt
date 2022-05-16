package com.example.data.utilities

import com.example.data.entities.User
import com.google.firebase.firestore.DocumentSnapshot

fun convertUserDocumentToEntity(id: String, document: DocumentSnapshot): User {
    val name: String = document.data?.get(UserDocumentProperties.name)!! as String
    val email: String = document.data?.get(UserDocumentProperties.email) as String
    return User(id, name, email)
}

fun convertUserEntityToDocument(user: User): HashMap<String, String?> {
    return hashMapOf(
        UserDocumentProperties.name to user.name,
        UserDocumentProperties.email to user.email,
    )
}