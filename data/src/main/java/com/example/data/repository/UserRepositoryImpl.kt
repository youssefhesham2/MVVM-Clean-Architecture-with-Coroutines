package com.example.data.repository

import com.example.data.datasources.remote.NetworkModule
import com.example.data.datasources.remote.UserApiService
import com.example.data.mapper.toDomainEntity
import com.example.domain.repository.UserRepository
import com.example.domain.utils.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl constructor(
    val apiClient: UserApiService = NetworkModule.apiClient(),
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    UserRepository {

    override suspend fun getUsers(): ResultData {
        return withContext(coroutineDispatcher) {
            try {
                val response = apiClient.getUsers()
                if (response.isEmpty()) ResultData.Empty("")
                else ResultData.Successful(response.map {
                    it.toDomainEntity()
                })
            } catch (exception: Exception) {
                ResultData.Exception(exception)
            }
        }
    }
}