package com.example.domain.usecases

import com.example.domain.models.TaskModel
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskListUseCaseImpl @Inject constructor(val taskRepository: TaskRepository) : TaskListUseCase {
    override suspend fun getTasksList() : Flow<List<TaskModel>?> {
        return taskRepository.getTaskList()
    }

    override suspend fun addNewTask() : Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id : String) : Boolean {
        TODO("Not yet implemented")
    }
}