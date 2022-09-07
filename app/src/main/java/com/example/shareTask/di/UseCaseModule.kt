package com.example.shareTask.di

import com.example.domain.repository.TaskRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecases.AuthenticateUserUseCase
import com.example.domain.usecases.AuthenticateUserUseCaseImpl
import com.example.domain.usecases.TaskListUseCase
import com.example.domain.usecases.TaskListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideAuthenticateUserUseCase(userRepository: UserRepository): AuthenticateUserUseCase {
        return AuthenticateUserUseCaseImpl(userRepository = userRepository)
    }

    @Provides
    @Singleton
    fun provideTaskListUseCase(taskRepository: TaskRepository): TaskListUseCase {
        return TaskListUseCaseImpl(taskRepository = taskRepository)
    }
}