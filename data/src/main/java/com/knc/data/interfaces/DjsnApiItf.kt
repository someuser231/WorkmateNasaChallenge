package com.knc.data.interfaces

import com.knc.domain.models.DjsnProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DjsnApiItf {
    @GET("products/{id}")
    suspend fun getProducts(
        @Path("id") id: Int
    ) : DjsnProductModel
}