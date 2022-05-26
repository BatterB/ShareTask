package com.example.data.utilities

import androidx.room.TypeConverter
import com.example.data.entities.Task
import com.example.data.entities.User
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

fun convertUserDocumentToEntity(id: String, document: DocumentSnapshot): User {
    val name: String = document.data?.get(UserDocumentProperties.name)!! as String
    val email: String = document.data?.get(UserDocumentProperties.email) as String
    val tasks: List<String> = document.data?.get(UserDocumentProperties.tasks) as List<String>
    return User(id, name, email, tasks.toList())
}

fun convertTaskDocumentToEntity( document: DocumentSnapshot): Task {
    val id = document.id
    val title: String = document.data?.get(TaskDocumentProperties.title)!! as String
    val taskPoints: Map<String,Boolean> = document.data?.get(TaskDocumentProperties.taskPoints) as Map<String,Boolean>
    val priority : Long = document.data?.get(TaskDocumentProperties.priority) as Long
    val date : Date = document.getTimestamp(TaskDocumentProperties.date)!!.toDate()
    return Task(id, title, date, taskPoints, priority)
}
