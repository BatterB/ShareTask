package com.example.data.utilities

import com.example.data.entities.Task
import com.example.data.entities.User
import com.example.domain.models.TaskModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

fun convertUserDocumentToEntity(id: String, document: DocumentSnapshot): User {
    val name: String = document.data?.get(UserDocumentProperties.name)!! as String
    val email: String = document.data?.get(UserDocumentProperties.email) as String
    val tasks: List<String> = document.data?.get(UserDocumentProperties.tasks) as List<String>
    return User(id, name, email, tasks.toList())
}

fun convertTaskDocumentToEntity(document: DocumentSnapshot): Task {
    val id = document.id
    val title: String = document.data?.get(TaskDocumentProperties.title)!! as String
    val taskPoints: List<List<String>> = converterJsonToListOfList(
        document.data?.get(TaskDocumentProperties.taskPoints) as String)
    val priority : Long = document.data?.get(TaskDocumentProperties.priority) as Long
    val date : Date = document.getTimestamp(TaskDocumentProperties.date)!!.toDate()
    return Task(id, title, date, taskPoints, priority)
}

fun converterTaskModelToJson(task : TaskModel) : String?{
    return Gson().toJson(task)
}

fun converterJsonToTaskModel(string: String?) : TaskModel{
    return Gson().fromJson(
        string,
        object : TypeToken<TaskModel>() {}.type
    )
}

fun converterListOfListToJson(list : List<List<String?>>) : String?{
    return Gson().toJson(list)
}


fun converterJsonToListOfList(string: String?) : List<List<String>>{
    return Gson().fromJson(
        string,
        object : TypeToken<List<List<String>>>() {}.type
    )
}
