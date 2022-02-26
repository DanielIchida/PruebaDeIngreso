package com.daniel.users

import com.daniel.domain.User
import com.daniel.repository.UserRepository

class GetFindUser(private val userRepository: UserRepository) {
    suspend fun invoke(id: Int) : User = userRepository.findUser(id)
}