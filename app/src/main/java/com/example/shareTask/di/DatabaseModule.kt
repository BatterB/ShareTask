package com.example.shareTask.di

import android.content.Context
import androidx.room.Room
import com.example.data.ShareTaskDatabase
import com.example.data.dao.TaskDao
import com.example.data.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule{
    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): ShareTaskDatabase {
        return Room.databaseBuilder(
            appContext,
            ShareTaskDatabase::class.java,
            "share_task.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: ShareTaskDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: ShareTaskDatabase) : TaskDao {
        return database.taskDao()
    }
}