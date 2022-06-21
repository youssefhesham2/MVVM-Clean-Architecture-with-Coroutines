package com.example.data.datasources.remote

import com.example.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    companion object {
        private var ApiClient: UserApiService? = null
        fun apiClient(): UserApiService {
            ApiClient?.let { ApiClient } ?: let {
                ApiClient = Retrofit.Builder()
                    .baseUrl(BuildConfig.USER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserApiService::class.java)
            }
            return ApiClient!!
        }
    }
}
