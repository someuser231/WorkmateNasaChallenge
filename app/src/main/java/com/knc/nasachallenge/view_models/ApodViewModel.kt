package com.knc.nasachallenge.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.knc.data.repo.ApodRepoImp
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.flow.Flow

open class ApodViewModel: ViewModel() {
    val pagingData: Flow<PagingData<ApodModel>> by lazy {
        ApodRepoImp().loadPaging().cachedIn(viewModelScope)
    }
}