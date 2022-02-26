package com.daniel.pruebadeingreso.ui.users

import androidx.lifecycle.SavedStateHandle
import com.daniel.repository.UserRepository
import com.daniel.users.GetFindUser
import com.daniel.users.GetListUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UserActivityModel{

    @Provides
    @Named("userId")
    fun saveIdProvider(stateHandle: SavedStateHandle): String =
        stateHandle.get<String>("USERID")
            ?: throw IllegalStateException("User Id not found in the state handle")

    @Provides
    fun getListUsers(userRepository: UserRepository) =
        GetListUsers(userRepository)

    @Provides
    fun getFindUser(userRepository: UserRepository) =
        GetFindUser(userRepository)

}