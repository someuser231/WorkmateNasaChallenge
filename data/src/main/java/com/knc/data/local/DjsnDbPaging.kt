package com.knc.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.knc.domain.models.DjsnProductModel

class DjsnDbPaging(val db: MainDb): PagingSource<Int, DjsnProductModel>() {
    override fun getRefreshKey(state: PagingState<Int, DjsnProductModel>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DjsnProductModel> {
        return try {
            val pageOffset = 10
            val page = params.key ?: 1
            val nextKey = page + 1 + pageOffset

            val response = ArrayList<DjsnProductModel>()

            for (i in page..nextKey-1) {
                response.add(
                    db.dbItemToModel(
                        db.getDjsnDao().getProduct(i)
                    )
                )
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