package com.daniel.framework.databasemanager.dao

import androidx.room.Dao
import androidx.room.Query
import com.daniel.framework.databasemanager.entities.Users

@Dao
interface UsersDao : BaseDao<Users> {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun findAllUsers() : List<Users>

    @Query("SELECT * FROM users WHERE id = :id")
    fun findIdUser(id: String) : Users

    @Query("SELECT COUNT(*) FROM users")
    fun count() : Int

}