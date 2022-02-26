package com.daniel.framework.databasemanager.dao

import androidx.room.Dao
import androidx.room.Query
import com.daniel.framework.databasemanager.entities.Posts

@Dao
interface PostsDao : BaseDao<Posts> {

    @Query("SELECT * FROM posts WHERE id_user = :idUser")
    fun findAllPostByUser(idUser: String) : List<Posts>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun findIdPost(id: Int): Posts

    @Query("SELECT COUNT(*) FROM posts WHERE id_user = :idUser")
    fun countByUser(idUser: String) : Int

}