package com.example.shareTask.presentation.tasks

import android.view.View
import com.example.domain.models.TaskModel

interface TaskActionListener{
    fun onOpenTask(task : TaskModel,view : View)

    fun onShareTask(task: TaskModel)

    fun onDeleteTask(task : TaskModel)
}