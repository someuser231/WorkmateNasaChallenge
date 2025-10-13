package com.knc.data.interfaces

import com.knc.domain.models.ApodModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiItf {
//    @GET("apod?api_key=CpZhGPxb5LK0Pvd7VMhqrbTfRo1SLge0Qer6nbwb&start_date=2025-01-01&end_date=2025-02-01")
    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null,
        @Query("concept_tags") conceptTags: Boolean? = null,
        @Query("hd") hd: Boolean? = null,
        @Query("count") count: Int? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null,
        @Query("thumbs") thumbs: Boolean? = null
    ): ArrayList<ApodModel>
}