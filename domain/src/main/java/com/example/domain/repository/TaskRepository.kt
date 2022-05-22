package com.example.domain.repository

import com.example.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    suspend fun getTaskList() : Flow<List<TaskModel>?>
    suspend fun addNewTask() : Boolean
    suspend fun deleteTask(id:String) : Boolean
}