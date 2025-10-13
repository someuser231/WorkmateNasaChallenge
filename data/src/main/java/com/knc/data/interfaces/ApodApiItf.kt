package com.knc.data.interfaces

import com.knc.domain.models.ApodModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiItf {
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