package com.daniel.posts

import com.daniel.domain.Post
import com.daniel.repository.PostRepository

class GetFindPost(private val postRepository: PostRepository) {
    suspend fun invoke(id: Int): Post = postRepository.findPost(id)
}