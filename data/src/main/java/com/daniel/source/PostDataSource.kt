package com.daniel.source

import com.daniel.domain.Post

interface PostDataSource : LocalDataSource<Post> {
    suspend fun findAllByUser(idUser: String) : List<Post>
    suspend fun isEmptyByUser(idUser: String) : Boolean
}