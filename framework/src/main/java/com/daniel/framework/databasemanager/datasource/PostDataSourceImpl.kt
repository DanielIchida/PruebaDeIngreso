package com.daniel.framework.databasemanager.datasource

import com.daniel.domain.Post
import com.daniel.framework.databasemanager.AppDataBase
import com.daniel.framework.mappers.toDomainPost
import com.daniel.framework.mappers.toRoomPosts
import com.daniel.source.PostDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostDataSourceImpl(db: AppDataBase) : PostDataSource {

    private val postDao = db.postDao()

    override suspend fun findAllByUser(idUser: String): List<Post> = withContext(Dispatchers.IO){
        postDao.findAllPostByUser(idUser).map { it.toDomainPost() }
    }

    override suspend fun isEmptyByUser(idUser: String): Boolean = withContext(Dispatchers.IO){
        postDao.countByUser(idUser) <= 0
    }

    override suspend fun isEmpty(): Boolean = false

    override suspend fun sync(data: List<Post>) = withContext(Dispatchers.IO){
        postDao.saveAll(data.map { it.toRoomPosts() })
    }

    override suspend fun findAll(): List<Post> = listOf()

    override suspend fun findById(id: Int?): Post = withContext(Dispatchers.IO){
        postDao.findIdPost(id!!).toDomainPost()
    }
}