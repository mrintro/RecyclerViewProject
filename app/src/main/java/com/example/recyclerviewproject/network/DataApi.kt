package com.example.recyclerviewproject.network

import com.example.recyclerviewproject.response.userData
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface DataApi {

    @FormUrlEncoded
    @GET("public/v1/users")
    suspend fun getDataFromApi() : userData

}