package com.knc.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.knc.data.interfaces.DjsnApiItf
import com.knc.domain.models.DjsnProductModel

class DjsnApiPaging(val djsnApi: DjsnApiItf): PagingSource<Int, DjsnProductModel>() {
    override fun getRefreshKey(state: PagingState<Int, DjsnProductModel>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DjsnProductModel> {
        return try {
            val pageOffset = 10
            val page = params.key ?: 1
            val nextKey = page + 1 + pageOffset
            val response = mutableListOf<DjsnProductModel>()

            for (i in page..nextKey-1) {
                response.add(djsnApi.getProducts(i))
            }

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey,
            )

        } catch (e: Exception) {
            println(e)
            LoadResult.Error(e)
        }
    }
}