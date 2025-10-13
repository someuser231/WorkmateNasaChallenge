package com.knc.data.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.knc.data.interfaces.ApodApiItf
import com.knc.data.remote.ApodApiPaging
import com.knc.data.remote.ApodApiUtils
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class ApodRepoImp: ApodRepoItf, ViewModel() {
    val liveData = ApodLiveData()
    lateinit var paging: ApodApiPaging

    override fun loadItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val apodApi = ApodApiUtils.getInstance().create(ApodApiItf::class.java)
//            val apods = apodApi.getApod(apiKey = ApodApiUtils.API_KEY, count = 10)
            paging = ApodApiPaging(apodApi)
//            liveData.postValue(apods)
        }
    }

    fun loadPaging(): Flow<PagingData<ApodModel>> {
        val apodApi = ApodApiUtils.getInstance().create(ApodApiItf::class.java)
        paging = ApodApiPaging(apodApi)
        return Pager(
            config = PagingConfig(pageSize = 30, maxSize = 200),
            pagingSourceFactory = {
                paging
            }
        ).flow.cachedIn(viewModelScope)
    }
}