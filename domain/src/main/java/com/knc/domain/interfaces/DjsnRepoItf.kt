package com.knc.domain.interfaces

import androidx.paging.PagingData
import com.knc.domain.models.DjsnProductModel
import kotlinx.coroutines.flow.Flow

interface DjsnRepoItf {
    fun loadItemsApi(): Flow<PagingData<DjsnProductModel>>
    fun loadItemsDb(): Flow<PagingData<DjsnProductModel>>

    fun insertIntoDb(djsnPrd: DjsnProductModel)
}