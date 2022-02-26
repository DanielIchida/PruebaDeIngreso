package com.daniel.framework.networkmanager

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceService {

  @GET("users")
  suspend fun getUsers() : Response<List<UsersResult>>

  @GET("posts")
  suspend fun getPostByUser(@Query("userId") userId: String) : Response<List<PostResult>>

}