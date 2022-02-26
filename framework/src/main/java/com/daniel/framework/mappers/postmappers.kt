package com.daniel.framework.mappers

import com.daniel.domain.Post
import com.daniel.framework.databasemanager.entities.Posts

fun Post.toRoomPosts() : Posts =
    Posts(
        id,
        title,
        body,
        idUser
    )

fun Posts.toDomainPost(): Post =
    Post(
        id,
        title,
        body,
        idUser
    )