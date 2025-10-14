package com.knc.data.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.knc.data.interfaces.ApodApiItf
import com.knc.data.local.ApodDbPaging
import com.knc.data.local.MainDb
import com.knc.data.remote.ApodApiPaging
import com.knc.data.remote.ApodApiUtils
import com.knc.domain.interfaces.ApodRepoItf
import com.knc.domain.models.ApodModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ApodRepoImp(val db: MainDb): ApodRepoItf, ViewModel() {
    override fun loadItemsApi(): Flow<PagingData<ApodModel>> {
        val apodApi = ApodApiUtils.getInstance().create(ApodApiItf::class.java)
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                ApodApiPaging(apodApi)
            }
        ).flow.map { data ->
            data.filter { apod ->
                apod.url != null && apod.media_type == "image"
            }
        }
    }

    override fun loadItemsDb(): Flow<PagingData<ApodModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 200
            ),
            pagingSourceFactory = {
                ApodDbPaging(db)
            }
        ).flow.map { data ->
            data.filter { apod ->
                apod.url != null && apod.media_type == "image"
            }
        }
    }

    override fun insertIntoDb(apodModel: ApodModel) {
        viewModelScope.launch {
            db.getApodDao().insertApod(
                db.modelToDbItemApod(apodModel)
            )
        }
    }
}