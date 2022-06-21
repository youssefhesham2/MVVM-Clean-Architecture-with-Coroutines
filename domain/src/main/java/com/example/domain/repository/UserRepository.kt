package com.example.domain.repository

import com.example.domain.entities.UserDomainEntity
import com.example.domain.utils.ResultData

interface UserRepository {
    suspend fun getUsers(): ResultData
}