package com.daniel.source

interface LocalDataSource<T> : Repository<T> {
    suspend fun isEmpty() : Boolean
    suspend fun sync(data: List<T>)
}