package com.daniel.users

import com.daniel.domain.User
import com.daniel.repository.UserRepository

class GetListUsers(private val userRepository: UserRepository){
     suspend fun invoke() : List<User> = userRepository.getUsers()
}