package com.example.shareTask.presentation.taskWindow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.TaskModel
import com.example.domain.usecases.TaskListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskWindowViewModel @Inject constructor(val taskListUseCase: TaskListUseCase) : ViewModel() {

    private var _taskPointsList = MutableLiveData<List<List<String>>?>()
    val taskPointsList : LiveData<List<List<String>>?>
        get() = _taskPointsList

    fun newPoint(title: String,taskId : String){
        viewModelScope.launch {
           _taskPointsList.value =  taskListUseCase.addTaskPoint(title, taskId)
        }

    }
}