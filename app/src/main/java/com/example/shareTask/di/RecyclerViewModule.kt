package com.example.shareTask.di

import com.example.shareTask.presentation.tasks.TaskActionListener
import com.example.shareTask.presentation.tasks.TaskActionListenerImpl
import com.example.shareTask.presentation.tasks.TasksAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewModule {

    @Provides
    fun provideTaskAdapter(taskActionListener: TaskActionListener) : TasksAdapter{
        return TasksAdapter(taskActionListener)
    }

    @Provides
    fun provideTaskActionListener() : TaskActionListener{
        return TaskActionListenerImpl()
    }
}