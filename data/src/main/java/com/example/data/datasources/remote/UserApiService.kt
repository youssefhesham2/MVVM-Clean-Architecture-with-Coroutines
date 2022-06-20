package com.example.data.datasources.remote

import com.example.data.entities.User
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}