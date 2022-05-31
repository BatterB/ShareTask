package com.example.shareTask.di

import android.content.Context
import com.example.domain.usecases.AuthenticateUserUseCase
import com.example.domain.usecases.TaskListUseCase
import com.example.shareTask.presentation.settings.SettingsViewModelFactory
import com.example.shareTask.presentation.taskWindow.TaskWindowViewModelFactory
import com.example.shareTask.presentation.tasks.TasksViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule(private val context: Context) {
    @Provides
    fun provideContext() : Context{
        return context
    }

    @Provides
    @Singleton
    fun provideTaskViewModel (taskListUseCaseImpl: TaskListUseCase) : TasksViewModelFactory{
        return TasksViewModelFactory(taskListUseCaseImpl)
    }

    @Provides
    @Singleton
    fun provideTaskWindowViewModel () : TaskWindowViewModelFactory {
        return TaskWindowViewModelFactory()
    }

    @Provides
    @Singleton
    fun provideSettingsViewModel (authenticateUserUseCase : AuthenticateUserUseCase) : SettingsViewModelFactory {
        return SettingsViewModelFactory(authenticateUserUseCase)
    }



}