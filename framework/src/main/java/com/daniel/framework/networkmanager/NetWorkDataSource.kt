package com.daniel.framework.networkmanager

import com.daniel.domain.Post
import com.daniel.domain.User
import com.daniel.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetWorkDataSource : RemoteDataSource{

    override suspend fun getListUsers(): List<User> = withContext(Dispatchers.IO){
        val usersResult = JsonPlacerRest.serviceJsonPlace.getUsers()
        val users = mutableListOf<User>()
        if(usersResult.isSuccessful){
           usersResult.body()?.forEach { it ->
               val user = User(it.id.toString(),it.name,it.email,it.phone)
               users.add(user)
           }
        }
        return@withContext users
    }

    override suspend fun getListPost(idUser: Int): List<Post> = withContext(Dispatchers.IO){
        val postResult = JsonPlacerRest.serviceJsonPlace.getPostByUser(idUser.toString())
        val posts = mutableListOf<Post>()
        if(postResult.isSuccessful){
            postResult.body()?.forEach { it ->
                val post = Post(0,it.title,it.body,it.userId)
                posts.add(post)
            }
        }
        return@withContext posts
    }
}