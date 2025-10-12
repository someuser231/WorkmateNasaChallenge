package com.knc.data.remote

import android.util.Log
import com.knc.data.interfaces.ApodApiItf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ApodApi {
    fun getItems() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apodApi = retrofit.create(ApodApiItf::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val apod = apodApi.getApod()
            Log.d("Retrofit", apod[0].title!!)
        }
    }
}