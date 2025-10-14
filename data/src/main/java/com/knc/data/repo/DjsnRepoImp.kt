package com.knc.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.knc.data.interfaces.DjsnApiItf
import com.knc.data.remote.DjsnApiPaging
import com.knc.data.remote.DjsnApiUtils
import com.knc.domain.interfaces.DjsnRepoItf
import com.knc.domain.models.DjsnProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DjsnRepoImp: DjsnRepoItf {
    override fun loadItems(): Flow<PagingData<DjsnProductModel>> {
        val djsnApi = DjsnApiUtils.getInstance().create(DjsnApiItf::class.java)
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 200
            ),
            pagingSourceFactory = {
                DjsnApiPaging(djsnApi)
            }
        ).flow
    }
}