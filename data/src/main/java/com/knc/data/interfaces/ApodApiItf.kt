package com.knc.data.interfaces

import com.knc.domain.models.ApodModel
import retrofit2.http.GET

interface ApodApiItf {
    @GET("apod?api_key=CpZhGPxb5LK0Pvd7VMhqrbTfRo1SLge0Qer6nbwb&start_date=2025-01-01&end_date=2025-02-01")
    suspend fun getApod(): ArrayList<ApodModel>
}