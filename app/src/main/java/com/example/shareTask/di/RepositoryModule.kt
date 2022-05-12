package com.example.shareTask.di

import com.example.data.dao.UserDao
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository{
        return UserRepositoryImpl(userDao)
    }
}