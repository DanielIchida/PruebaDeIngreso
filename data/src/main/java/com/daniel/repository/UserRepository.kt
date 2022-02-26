package com.daniel.repository

import com.daniel.domain.User
import com.daniel.source.LocalDataSource
import com.daniel.source.RemoteDataSource

class UserRepository(
    private val localDataSource: LocalDataSource<User>,
    private val remoteDataSource: RemoteDataSource
){

     suspend fun getUsers() : List<User>{
          if(localDataSource.isEmpty()){
               val users = remoteDataSource.getListUsers()
               localDataSource.sync(users)
          }
          return localDataSource.findAll()
     }

     suspend fun findUser(id: Int) : User{
         return localDataSource.findById(id)
     }

}