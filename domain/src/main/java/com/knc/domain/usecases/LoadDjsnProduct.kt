package com.knc.domain.usecases

import androidx.paging.PagingData
import com.knc.domain.interfaces.DjsnRepoItf
import com.knc.domain.models.DjsnProductModel
import kotlinx.coroutines.flow.Flow

class LoadDjsnProduct(val djsnRepo: DjsnRepoItf) {
    fun execute(): Flow<PagingData<DjsnProductModel>> {
        return djsnRepo.loadItems()
    }
}