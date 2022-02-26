package com.daniel.source

interface Repository<T> {
    suspend fun findAll(): List<T>
    suspend fun findById(id: Int?) : T
}