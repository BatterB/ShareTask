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
import com.example.data.utilities.convertUserDocumentToEntity
import com.example.domain.models.TaskModel
import com.example.domain.repository.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao, val userDao: UserDao) : TaskRepository{

    override suspend fun getTaskList(): Flow<List<TaskModel>?> {
        val db = Firebase.firestore
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


    override suspend fun addNewTask(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: String): Boolean {
        TODO("Not yet implemented")
    }

}