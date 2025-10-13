package com.knc.nasachallenge.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.knc.data.repo.ApodRepoImp
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.models.ApodModel
import com.knc.domain.usecases.LoadItems
import kotlinx.coroutines.flow.Flow

open class ApodViewModel: ViewModel() {
    val apodRepo: ApodRepoItf by lazy {
        ApodRepoImp()
    }
    val pagingData: Flow<PagingData<ApodModel>> by lazy {
        LoadItems(apodRepo).execute().cachedIn(viewModelScope)
    }
    lateinit var apodItem: ApodModel
}