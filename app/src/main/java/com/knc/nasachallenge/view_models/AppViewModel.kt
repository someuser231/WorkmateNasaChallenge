package com.knc.nasachallenge.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.knc.data.local.MainDb
import com.knc.data.repo.ApodRepoImp
import com.knc.data.repo.DjsnRepoImp
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.interfaces.DjsnRepoItf
import com.knc.domain.models.ApodModel
import com.knc.domain.models.DjsnProductModel
import com.knc.domain.usecases.LoadApodApi
import com.knc.domain.usecases.LoadDjsnProductApi
import com.knc.domain.usecases.LoadDjsnProductDb
import kotlinx.coroutines.flow.Flow

open class AppViewModel(): ViewModel() {
    lateinit var apodItem: ApodModel
    lateinit var djsnPrdItem: DjsnProductModel
    lateinit var mainDb: MainDb
    val isConnected: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


    val apodRepo: ApodRepoItf by lazy {
        ApodRepoImp()
    }
    val pagingApod: Flow<PagingData<ApodModel>> by lazy {
        LoadApodApi(apodRepo).execute().cachedIn(viewModelScope)
    }

    val djsnRepo: DjsnRepoItf by lazy {
        DjsnRepoImp(mainDb)
    }
    val pagingDjsnPrdApi: Flow<PagingData<DjsnProductModel>> by lazy {
        LoadDjsnProductApi(djsnRepo).execute().cachedIn(viewModelScope)
    }
    val pagingDjsnPrdDb: Flow<PagingData<DjsnProductModel>> by lazy {
        LoadDjsnProductDb(djsnRepo).execute().cachedIn(viewModelScope)
    }


}