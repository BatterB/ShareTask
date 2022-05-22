package com.example.shareTask.presentation.tasks

import androidx.lifecycle.*
import com.example.domain.models.TaskModel
import com.example.domain.usecases.TaskListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksViewModel @Inject constructor(val tasksUseCase: TaskListUseCase) : ViewModel() {

    private var _taskList = MutableLiveData<List<TaskModel>?>()

    val taskList : LiveData<List<TaskModel>?>
        get() = _taskList

    fun uploadTasks (){
        viewModelScope.launch {
            tasksUseCase.getTasksList().collect{_taskList.value = it}
        }
    }

}