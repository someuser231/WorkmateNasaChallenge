package com.knc.data.remote

import com.knc.data.interfaces.ApodApiItf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApodApiUtils {
    const val BASE_URL: String = "https://api.nasa.gov/planetary/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}