package com.example.shareTask.presentation.tasks

import android.os.Bundle
import android.view.View

import androidx.navigation.Navigation.findNavController
import com.example.data.utilities.converterTaskModelToJson
import com.example.domain.models.TaskModel
import com.example.shareTask.R

class TaskActionListenerImpl : TaskActionListener {
    override fun onOpenTask(task: TaskModel,view : View) {
        val bundle = Bundle()
        bundle.putString("ARG_TASK", converterTaskModelToJson(task))
        findNavController(view).navigate(R.id.taskWindowFragment, bundle)
    }

    override fun onShareTask(task: TaskModel) {
        TODO("Not yet implemented")
    }

    override fun onDeleteTask(task: TaskModel) {
        TODO("Not yet implemented")
    }

}