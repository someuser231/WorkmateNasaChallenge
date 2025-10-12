package com.knc.domain.models

data class ApodModel(
    val id: Int,
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val mediaType: String?,
    val serviceVersion: String?,
    val title: String?,
    val url: String?,
)
