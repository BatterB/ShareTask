package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.TaskModel
import java.util.*

@Entity
data class Task(
    @PrimaryKey
    val id : String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "task_points")
    var taskPoints: List<String>,

    @ColumnInfo(name = "priority")
    var priority: Long

)

fun Task.asDomainModel(): TaskModel {
    return TaskModel(
        id = this.id,
        title = this.title,
        date = this.date,
        taskPoints = this.taskPoints,
        priority = this.priority
    )
}