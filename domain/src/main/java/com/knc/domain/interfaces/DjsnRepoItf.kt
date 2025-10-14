package com.knc.domain.interfaces

import androidx.paging.PagingData
import com.knc.domain.models.DjsnProductModel
import kotlinx.coroutines.flow.Flow

interface DjsnRepoItf {
    fun loadItems(): Flow<PagingData<DjsnProductModel>>
}