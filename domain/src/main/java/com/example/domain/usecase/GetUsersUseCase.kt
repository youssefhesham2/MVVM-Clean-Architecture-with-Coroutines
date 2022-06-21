package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import com.example.domain.utils.ResultData

class GetUsersUseCase constructor(private val userRepository: UserRepository) {

    suspend fun getUsers(): ResultData {
        return userRepository.getUsers()
    }
}