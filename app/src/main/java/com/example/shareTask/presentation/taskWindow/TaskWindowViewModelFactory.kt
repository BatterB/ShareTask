package com.example.shareTask.presentation.taskWindow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shareTask.presentation.tasks.TasksViewModel

class TaskWindowViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskWindowViewModel::class.java)) {
            return TaskWindowViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}