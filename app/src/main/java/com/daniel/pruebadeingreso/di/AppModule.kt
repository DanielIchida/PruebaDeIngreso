package com.daniel.pruebadeingreso.di

import android.app.Application
import androidx.room.Room
import com.daniel.domain.User
import com.daniel.framework.databasemanager.AppDataBase
import com.daniel.framework.databasemanager.dao.PostsDao
import com.daniel.framework.databasemanager.datasource.PostDataSourceImpl
import com.daniel.framework.databasemanager.datasource.UserDataSource
import com.daniel.framework.networkmanager.NetWorkDataSource
import com.daniel.source.LocalDataSource
import com.daniel.source.PostDataSource
import com.daniel.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "users-db"
    ).build()

    @Provides
    fun networkDataSources(): RemoteDataSource = NetWorkDataSource()

    @Provides
    fun userDataSource(db: AppDataBase): LocalDataSource<User> =
        UserDataSource(db)

    @Provides
    fun postDataSource(db: AppDataBase) : PostDataSource =
        PostDataSourceImpl(db)

}