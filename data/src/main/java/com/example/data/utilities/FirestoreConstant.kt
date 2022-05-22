package com.example.data.utilities

import java.sql.Timestamp

object CollectionNames {
    const val users = "users"
    const val tasks = "tasks"
}

object UserDocumentProperties {
    const val name = "name"
    const val email = "email"
    const val tasks = "tasks"
}

object TaskDocumentProperties {
    const val title = "title"
    const val taskPoints = "task_points"
    const val priority = "priority"
    const val date = "date"
}