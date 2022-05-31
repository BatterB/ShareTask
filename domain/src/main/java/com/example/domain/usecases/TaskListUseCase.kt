package com.example.domain.usecases

import com.example.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TaskListUseCase {
    suspend fun getTasksList() : Flow<List<TaskModel>?>
    suspend fun addNewTask(title: String,priority : String,date: Date) : Boolean
    suspend fun deleteTask(id : String)
    suspend fun updateTaskFromLocalDB() : Flow<List<TaskModel>?>
}