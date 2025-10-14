package com.knc.domain.usecases

import androidx.paging.PagingData
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.flow.Flow

class LoadApodDb(private val apodRepo: ApodRepoItf) {
    fun execute(): Flow<PagingData<ApodModel>> {
        return apodRepo.loadItemsDb()
    }
}