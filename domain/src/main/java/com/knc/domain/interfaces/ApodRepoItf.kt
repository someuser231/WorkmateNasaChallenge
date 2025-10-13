package com.knc.domain.interfaces

import androidx.paging.PagingData
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.flow.Flow

interface ApodRepoItf {
    fun loadItems(): Flow<PagingData<ApodModel>>
}