package com.example.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.data.dao.TaskDao
import com.example.data.dao.UserDao
import com.example.data.entities.Task
import com.example.data.entities.User
import com.example.data.entities.asDomainModel
import com.example.data.utilities.CollectionNames
import com.example.data.utilities.convertTaskDocumentToEntity
import com.example.data.utilities.converterListOfListToJson
import com.example.domain.models.TaskModel
import com.example.domain.repository.TaskRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao, val userDao: UserDao) : TaskRepository{

    private val db = Firebase.firestore

    override suspend fun getTaskList(): Flow<List<TaskModel>?> {
        val user: User? = userDao.getCurrentUser()
        val tasksIds = user?.tasks
        val allTasks = mutableListOf<Task>()

        db.collection(CollectionNames.tasks)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    allTasks.add(convertTaskDocumentToEntity(document))
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }.await()
        taskDao.insertAll(allTasks)

        return taskDao.getUserTasks(tasksIds).transform{ list -> emit(list?.map{it.asDomainModel()})}
    }


    override suspend fun addNewTask(title: String,priority : Int,date: Date): Boolean {
        var isSuccess = false

        val newTask = hashMapOf(
            "title" to title,
            "priority" to priority,
            "task_points" to converterListOfListToJson(listOf()),
            "date" to Timestamp(date)
        )

        val user: User? = userDao.getCurrentUser()
        val newTaskList = user?.tasks?.toMutableList()
        val generatedDoc = db.collection(CollectionNames.tasks).document()
        val userTasks = db.collection(CollectionNames.users).document(user!!.id)

        generatedDoc.set(newTask)
            .addOnSuccessListener { isSuccess = true }
            .addOnFailureListener { Log.e(TAG, "Error writing document") }

        userTasks.update(
            "tasks", FieldValue.arrayUnion(generatedDoc.id)
        )

        newTaskList?.add(generatedDoc.id)
        taskDao.insert(Task(generatedDoc.id,title,date, listOf(),priority.toLong()))
        user.tasks = newTaskList!!.toList()
        userDao.updateUser(user)
        return isSuccess
    }

    override suspend fun deleteTask(id: String) {
        val user: User? = userDao.getCurrentUser()

        db.collection(CollectionNames.users).document(user!!.id)
            .update("tasks",FieldValue.arrayRemove(id))

        db.collection(CollectionNames.tasks).document(id).delete()

        taskDao.deleteById(id)

        val newTaskList = user.tasks.toMutableList()
        newTaskList.removeAt(newTaskList.indexOf(id))
        user.tasks = newTaskList.toList()
        userDao.insert(user)
    }

    override suspend fun updateTaskFromLocalDB(): Flow<List<TaskModel>?> {
        val user: User? = userDao.getCurrentUser()
        val tasksIds = user?.tasks
        return taskDao.getUserTasks(tasksIds).transform{ list -> emit(list?.map{it.asDomainModel()})}
    }

}