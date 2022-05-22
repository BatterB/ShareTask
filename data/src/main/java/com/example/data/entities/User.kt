package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.UserModel

@Entity
data class User (
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,


    @ColumnInfo(name = "tasks")
    var tasks: List<String>
    )

fun User.asDomainModel(): UserModel {
    return UserModel(
        id = this.id,
        name = this.name,
        email = this.email
    )
}
