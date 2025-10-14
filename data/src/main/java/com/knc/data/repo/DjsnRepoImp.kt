package com.knc.data.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.knc.data.interfaces.DjsnApiItf
import com.knc.data.local.DjsnDbPaging
import com.knc.data.local.MainDb
import com.knc.data.remote.DjsnApiPaging
import com.knc.data.remote.DjsnApiUtils
import com.knc.domain.interfaces.DjsnRepoItf
import com.knc.domain.models.DjsnProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DjsnRepoImp(val db: MainDb): DjsnRepoItf, ViewModel() {
    override fun loadItemsApi(): Flow<PagingData<DjsnProductModel>> {
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

    override fun loadItemsDb(): Flow<PagingData<DjsnProductModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 200
            ),
            pagingSourceFactory = {
                DjsnDbPaging(db)
            }
        ).flow
    }

    override fun insertIntoDb(djsnPrd: DjsnProductModel) {
        viewModelScope.launch {
            db.getDjsnDao().insertProduct(
                db.modelToDbItemDjsn(djsnPrd)
            )
        }
    }


}