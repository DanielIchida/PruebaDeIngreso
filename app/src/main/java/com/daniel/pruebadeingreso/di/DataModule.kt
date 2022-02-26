package com.daniel.pruebadeingreso.di

import com.daniel.domain.User
import com.daniel.repository.PostRepository
import com.daniel.repository.UserRepository
import com.daniel.source.LocalDataSource
import com.daniel.source.PostDataSource
import com.daniel.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

   @Provides
   fun userRepositoryProvider(
       localDataSource: LocalDataSource<User>,
       remoteDataSource: RemoteDataSource,
   ) = UserRepository(localDataSource,remoteDataSource)

   @Provides
   fun postRepositoryProvider(
       postDataSource: PostDataSource,
       remoteDataSource: RemoteDataSource
   ) = PostRepository(postDataSource,remoteDataSource)

}