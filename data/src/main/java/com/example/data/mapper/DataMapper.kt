package com.example.data.mapper

import com.example.data.entities.User
import com.example.domain.entities.UserDomainEntity

    fun UserDomainEntity.toDataEntity() = User(id, name, email, avatar)
    fun User.toDomainEntity() = UserDomainEntity(id, name, email, avatar)