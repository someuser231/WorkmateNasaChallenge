package com.knc.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DjsnApiUtils {
    const val BASE_URL: String = "https://dummyjson.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}