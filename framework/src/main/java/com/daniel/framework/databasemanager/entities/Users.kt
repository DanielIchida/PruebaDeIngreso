package com.daniel.framework.databasemanager.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey var id: String,
    var name: String,
    var email: String,
    var phone: String
)