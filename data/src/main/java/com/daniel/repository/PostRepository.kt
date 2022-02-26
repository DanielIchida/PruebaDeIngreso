package com.daniel.repository

import com.daniel.domain.Post
import com.daniel.source.LocalDataSource
import com.daniel.source.PostDataSource
import com.daniel.source.RemoteDataSource

class PostRepository(
    private val postDataSource: PostDataSource,
    private val remoteDataSource: RemoteDataSource
)
{

    suspend fun getPostByUser(idUser: Int) :  List<Post>{
        if(postDataSource.isEmptyByUser(idUser.toString())){
            val posts = remoteDataSource.getListPost(idUser)
            postDataSource.sync(posts)
        }
        return postDataSource.findAllByUser(idUser.toString())
    }

    suspend fun findPost(id: Int): Post{
        return postDataSource.findById(id)
    }

}