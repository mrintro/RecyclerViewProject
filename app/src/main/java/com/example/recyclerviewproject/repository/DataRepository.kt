package com.example.recyclerviewproject.repository

import com.example.recyclerviewproject.network.DataApi

class DataRepository(
    private val api: DataApi
) : BaseRepository() {
    suspend fun getDataFromApi() = safeApiCall {
        api.getDataFromApi()
    }
}