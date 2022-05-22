package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.TaskDao
import com.example.data.dao.UserDao
import com.example.data.entities.Task
import com.example.data.entities.User
import com.example.data.utilities.Converters

@Database(
    entities = [User::class, Task::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ShareTaskDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}