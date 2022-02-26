package com.daniel.posts

import com.daniel.domain.Post
import com.daniel.repository.PostRepository

class GetListPost(private val postRepository: PostRepository) {
    suspend fun invoke(idUser: Int): List<Post> = postRepository.getPostByUser(idUser)
}