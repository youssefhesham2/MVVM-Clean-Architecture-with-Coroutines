package com.example.mvvmsampleapp.Main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.entities.UserDomainEntity
import com.example.domain.usecase.GetUsersUseCase
import com.example.domain.utils.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val getUsersUseCase: GetUsersUseCase = GetUsersUseCase(userRepository = UserRepositoryImpl())
) :
    ViewModel(), CoroutineScope {
    private val loading = MutableLiveData<String>()
    private val users = MutableLiveData<List<UserDomainEntity>>()
    private val empty = MutableLiveData<String>()
    private val failure = MutableLiveData<Throwable>()

    fun onFetchUsersBtnClicked() {
        fetchUsers()
    }

    private fun fetchUsers() {
        launch {
            val response = getUsersUseCase.getUsers()

            when (response) {
                is ResultData.Successful<*> -> users.postValue(response.result as List<UserDomainEntity>)

                is ResultData.Empty -> empty.postValue(response.message)

                is ResultData.Failure -> failure.postValue(response.exception)
            }

        }

    }

    fun getLoading(): LiveData<String> {
        return loading
    }

    fun getDataSuccess(): LiveData<List<UserDomainEntity>> {
        return users
    }

    fun getEmpty(): LiveData<String> {
        return empty
    }

    fun getFailure(): LiveData<Throwable> {
        return failure
    }

    override val coroutineContext: CoroutineContext
        get() = coroutineDispatcher
}