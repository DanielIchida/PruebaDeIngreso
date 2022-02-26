package com.daniel.source

import com.daniel.domain.Post
import com.daniel.domain.User

interface RemoteDataSource{
     suspend fun getListUsers() : List<User>
     suspend fun getListPost(idUser: Int): List<Post>
}