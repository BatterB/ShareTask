package com.example.domain.usecases

import com.example.domain.models.TaskModel
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class TaskListUseCaseImpl @Inject constructor(val taskRepository: TaskRepository) : TaskListUseCase {
    override suspend fun getTasksList() : Flow<List<TaskModel>?> {
        return taskRepository.getTaskList()
    }

    override suspend fun addNewTask(title: String,priority : String,date: Date) : Boolean {
        var intPriority : Int? = null
        when(priority){
            "Very high priority" -> intPriority = 5
            "High priority" -> intPriority = 4
            "Standard priority" -> intPriority = 3
            "Low priority" -> intPriority = 2
            "Very low priority" -> intPriority = 1
        }
        return taskRepository.addNewTask(title, intPriority!!, date)
    }

    override suspend fun deleteTask(id : String) {
        taskRepository.deleteTask(id)
    }

    override suspend fun updateTaskFromLocalDB(): Flow<List<TaskModel>?> {
        return taskRepository.updateTaskFromLocalDB()
    }

    override suspend fun addTaskPoint(taskPoint: String, taskId: String) : List<List<String>> {
        return taskRepository.addTaskPoint(taskPoint, taskId)
    }
}