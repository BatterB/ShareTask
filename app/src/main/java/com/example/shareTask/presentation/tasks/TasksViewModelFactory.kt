package com.example.shareTask.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.TaskListUseCase
import javax.inject.Inject


class TasksViewModelFactory @Inject constructor(val taskListUseCase: TaskListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TasksViewModel(tasksUseCase = taskListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}