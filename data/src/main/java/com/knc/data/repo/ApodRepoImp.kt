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
import kotlinx.coroutines.flow.Flow

class ApodRepoImp: ApodRepoItf, ViewModel() {
    lateinit var paging: ApodApiPaging

    override fun loadItems() {
    }

    fun loadPaging(): Flow<PagingData<ApodModel>> {
        val apodApi = ApodApiUtils.getInstance().create(ApodApiItf::class.java)
        paging = ApodApiPaging(apodApi)
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                paging
            }
        ).flow.cachedIn(viewModelScope)
    }
}