package com.daniel.framework.databasemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daniel.framework.databasemanager.dao.PostsDao
import com.daniel.framework.databasemanager.dao.UsersDao
import com.daniel.framework.databasemanager.entities.Posts
import com.daniel.framework.databasemanager.entities.Users

@Database(entities = [Users::class,Posts::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun userDao() : UsersDao
    abstract fun postDao() : PostsDao
}