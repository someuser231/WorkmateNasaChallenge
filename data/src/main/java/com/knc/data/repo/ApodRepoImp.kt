package com.knc.data.repo

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.knc.data.interfaces.ApodApiItf
import com.knc.data.remote.ApodApiPaging
import com.knc.data.remote.ApodApiUtils
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApodRepoImp: ApodRepoItf, ViewModel() {
    lateinit var paging: ApodApiPaging

    override fun loadItems(): Flow<PagingData<ApodModel>> {
        val apodApi = ApodApiUtils.getInstance().create(ApodApiItf::class.java)
        paging = ApodApiPaging(apodApi)
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                paging
            }
        ).flow.map { data ->
            data.filter { apod ->
                apod.url != null && apod.media_type == "image"
            }
        }
    }
}