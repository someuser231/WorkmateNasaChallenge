package com.knc.nasachallenge.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.knc.data.repo.ApodRepoImp
import com.knc.data.repo.DjsnRepoImp
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.interfaces.DjsnRepoItf
import com.knc.domain.models.ApodModel
import com.knc.domain.models.DjsnProductModel
import com.knc.domain.usecases.LoadApod
import com.knc.domain.usecases.LoadDjsnProduct
import kotlinx.coroutines.flow.Flow

open class AppViewModel: ViewModel() {
    lateinit var apodItem: ApodModel
    lateinit var djsnPrdItem: DjsnProductModel

    val apodRepo: ApodRepoItf by lazy {
        ApodRepoImp()
    }
    val pagingApod: Flow<PagingData<ApodModel>> by lazy {
        LoadApod(apodRepo).execute().cachedIn(viewModelScope)
    }

    val djsnRepo: DjsnRepoItf by lazy {
        DjsnRepoImp()
    }
    val pagingDjsnPrd: Flow<PagingData<DjsnProductModel>> by lazy {
        LoadDjsnProduct(djsnRepo).execute().cachedIn(viewModelScope)
    }

}