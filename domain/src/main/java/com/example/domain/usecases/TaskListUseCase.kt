package com.example.domain.usecases

import com.example.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskListUseCase {
    suspend fun getTasksList() : Flow<List<TaskModel>?>
    suspend fun addNewTask() : Boolean
    suspend fun deleteTask(id : String) : Boolean
}