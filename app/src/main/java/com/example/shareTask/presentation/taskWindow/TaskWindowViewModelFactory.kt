package com.example.shareTask.presentation.taskWindow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.TaskListUseCase
import com.example.shareTask.presentation.tasks.TasksViewModel
import javax.inject.Inject

class TaskWindowViewModelFactory @Inject constructor(
    val taskListUseCase : TaskListUseCase
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskWindowViewModel::class.java)) {
            return TaskWindowViewModel(taskListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}