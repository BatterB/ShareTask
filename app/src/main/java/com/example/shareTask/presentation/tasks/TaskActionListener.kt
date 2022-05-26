package com.example.shareTask.presentation.tasks

import com.example.domain.models.TaskModel

interface TaskActionListener{
    fun onOpenTask(task : TaskModel)

    fun onShareTask(task: TaskModel)

    fun onDeleteTask(task : TaskModel)
}