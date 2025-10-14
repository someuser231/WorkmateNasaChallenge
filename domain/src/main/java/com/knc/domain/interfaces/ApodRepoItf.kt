package com.knc.domain.interfaces

import androidx.paging.PagingData
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.flow.Flow

interface ApodRepoItf {
    fun loadItemsApi(): Flow<PagingData<ApodModel>>
    fun loadItemsDb(): Flow<PagingData<ApodModel>>

    fun insertIntoDb(apodModel: ApodModel)
}