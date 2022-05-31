package com.example.domain.repository

import com.example.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow
import java.util.*


interface TaskRepository {
    suspend fun getTaskList() : Flow<List<TaskModel>?>
    suspend fun addNewTask(title: String,priority :Int,date: Date) : Boolean
    suspend fun deleteTask(id:String)
    suspend fun updateTaskFromLocalDB() : Flow<List<TaskModel>?>
}