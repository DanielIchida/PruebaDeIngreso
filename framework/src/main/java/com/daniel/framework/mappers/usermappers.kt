package com.daniel.framework.mappers

import com.daniel.domain.User
import com.daniel.framework.databasemanager.entities.Users

fun User.toRoomUsers() : Users =
    Users(
        id,
        name,
        phone,
        email
    )

fun Users.toDomainUser(): User =
    User(
        id,
        name,
        phone,
        email
    )