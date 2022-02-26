package com.daniel.framework.databasemanager.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Posts(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String,
    var body: String,
    @ColumnInfo(name = "id_user")
    var idUser: String
)