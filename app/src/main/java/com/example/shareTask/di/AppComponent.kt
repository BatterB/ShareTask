package com.example.shareTask.di

import com.example.shareTask.presentation.authentication.LoginFragment
import com.example.shareTask.presentation.authentication.RegistrationFragment
import com.example.shareTask.presentation.tasks.TasksFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, ViewModelModule::class,
     UseCaseModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(tasksFragment: TasksFragment)
}