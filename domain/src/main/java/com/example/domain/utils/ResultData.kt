package com.example.domain.utils

sealed class ResultData {

    data class Loading(val message: String) : ResultData()

    data class Successful<T>(val result: T) : ResultData()

    data class InvalidData(val message: String) : ResultData()

    data class Empty(val message: String) : ResultData()

    data class Failure(val exception: Throwable) : ResultData()

    data class Exception(val exception: Throwable) : ResultData()

}