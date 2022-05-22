package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.User
import com.example.data.utilities.USER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM $USER_TABLE WHERE id = :id")
    fun getById(id: String): Flow<User>

    @Query("SELECT * FROM $USER_TABLE LIMIT 1")
    suspend fun getCurrentUser(): User?

    @Query("DELETE FROM  $USER_TABLE")
    suspend fun clear()
}