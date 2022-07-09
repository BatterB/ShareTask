package com.example.shareTask.presentation.tasks

import androidx.lifecycle.*
import com.example.domain.models.TaskModel
import com.example.domain.usecases.TaskListUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.cancellable
import java.util.*
import javax.inject.Inject

class TasksViewModel @Inject constructor(val tasksUseCase: TaskListUseCase) : ViewModel() {

    private var _taskList = MutableLiveData<List<TaskModel>?>()
    val taskList : LiveData<List<TaskModel>?>
        get() = _taskList

    fun uploadTasks (){
        viewModelScope.launch {
            tasksUseCase.getTasksList().collect{_taskList.value = it }
        }
    }

    fun deleteTask(task : TaskModel){
        viewModelScope.launch {
            tasksUseCase.deleteTask(task.id)
        }
    }


    fun createTask(title : String, priority : String,date: Date) {
        viewModelScope.launch{
            tasksUseCase.addNewTask(title, priority, date)
            tasksUseCase.updateTaskFromLocalDB().collect {
                _taskList.postValue(it)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
