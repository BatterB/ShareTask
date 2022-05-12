package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.UserDao
import com.example.data.entities.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class ShareTaskDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}