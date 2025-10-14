package com.knc.domain.models

data class DjsnProductModel(
    val id: Int,
    val title: String?,
    val description: String?,
    val images: ArrayList<String?>,
)
