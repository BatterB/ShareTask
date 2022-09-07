package com.example.data.dao

import androidx.room.*
import com.example.data.entities.Task
import com.example.data.utilities.TASKS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(category: List<Task>)

    @Query("DELETE FROM $TASKS_TABLE WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM $TASKS_TABLE WHERE id = :id")
    fun getById(id: String): Flow<Task>

    @Query("SELECT * FROM $TASKS_TABLE WHERE id in (:id)")
    fun getUserTasks(id: List<String>?): Flow<List<Task>?>

    @Query("DELETE FROM $TASKS_TABLE")
    suspend fun clear()
}