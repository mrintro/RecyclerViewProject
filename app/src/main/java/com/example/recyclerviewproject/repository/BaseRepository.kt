package com.example.recyclerviewproject.repository

import android.util.Log
import com.example.recyclerviewproject.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall : suspend () -> T
    ) : Resource<T> {
        Log.d("SafeApiCAll","$apiCall")
        return withContext(Dispatchers.IO){
            try {
                Log.d("SafeApiCAll","Trying")
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true,null, null)
                    }
                }
            }
        }
    }
}