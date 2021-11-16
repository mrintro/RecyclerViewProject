package com.example.recyclerviewproject.network

import android.util.Log
import com.example.recyclerviewproject.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class RemoteDataSource {

    companion object{
        private const val BASE_URL = "https://gorest.co.in/"

        fun<Api> buildApi(
            api : Class<Api>
        ) : Api {
            Log.d("RetroFit","inside buildApi")
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().also { client ->
                        if(BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor();
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
        }
    }

}