package com.example.domain.models

import java.sql.Timestamp
import java.util.*

data class TaskModel(val id : String,
                     val title : String,
                     val taskPoints : Map<String,Boolean>,
                     val priority :Long,
                     val date: Date)
