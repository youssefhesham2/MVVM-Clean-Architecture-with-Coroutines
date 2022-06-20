package com.example.data.datasources.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    companion object {
        lateinit var ApiClient: UserApiService
        fun apiClient(): UserApiService {
            return  Retrofit.Builder()
                    .baseUrl("https://5e510330f2c0d300147c034c.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserApiService::class.java)
            }
        }
    }
