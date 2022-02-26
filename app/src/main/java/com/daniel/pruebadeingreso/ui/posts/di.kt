package com.daniel.pruebadeingreso.ui.posts

import com.daniel.posts.GetFindPost
import com.daniel.posts.GetListPost
import com.daniel.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PostActivityModel{

    @Provides
    fun getListPostByUser(postRepository: PostRepository) =
        GetListPost(postRepository)

    @Provides
    fun getFindPost(postRepository: PostRepository) =
        GetFindPost(postRepository)

}