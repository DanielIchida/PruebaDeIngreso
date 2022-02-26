package com.daniel.framework.databasemanager.datasource

import com.daniel.domain.User
import com.daniel.framework.databasemanager.AppDataBase
import com.daniel.framework.mappers.toDomainUser
import com.daniel.framework.mappers.toRoomUsers
import com.daniel.source.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(db: AppDataBase) : LocalDataSource<User> {

    private val userDao = db.userDao();

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO){
        userDao.count() <= 0
    }

    override suspend fun sync(data: List<User>) = withContext(Dispatchers.IO){
        userDao.saveAll(data.map { it.toRoomUsers() })
    }

    override suspend fun findAll(): List<User> = withContext(Dispatchers.IO){
        userDao.findAllUsers().map { it.toDomainUser() }
    }

    override suspend fun findById(id: Int?): User = withContext(Dispatchers.IO){
        userDao.findIdUser(id.toString()).toDomainUser()
    }


}