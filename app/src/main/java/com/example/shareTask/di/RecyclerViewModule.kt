package com.example.shareTask.di

import com.example.shareTask.presentation.taskWindow.TaskWindowAdapter
import com.example.shareTask.presentation.tasks.TaskActionListener
import com.example.shareTask.presentation.tasks.TasksAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewModule {

    @Provides
    fun provideTaskAdapter(): TasksAdapter {
        return TasksAdapter()
    }

    @Provides
    fun provideTaskWindowAdapter(): TaskWindowAdapter {
        return TaskWindowAdapter()
    }
}