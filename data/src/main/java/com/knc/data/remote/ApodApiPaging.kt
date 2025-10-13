package com.knc.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.knc.data.interfaces.ApodApiItf
import com.knc.domain.models.ApodModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ApodApiPaging(val apodApi: ApodApiItf) : PagingSource<String, ApodModel>() {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun getRefreshKey(state: PagingState<String, ApodModel>): String? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApodModel> {
        return try {
            val page = params.key ?: getCurrentDate()
            val endDate = page
            val startDate = getPreviousDate(endDate, 30)

            val response = apodApi.getApod(apiKey = ApodApiUtils.API_KEY,
                startDate = startDate,
                endDate = endDate
            )
            val nextKey = if(response.isNotEmpty()) getPreviousDate(startDate, 1) else null

            LoadResult.Page(
                data = response.sortedByDescending { it.date },
                prevKey = null,
                nextKey = nextKey
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    fun getCurrentDate(): String {
        return dateFormat.format(Date())
    }

    fun getPreviousDate(date: String, days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(date) ?: Date()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return dateFormat.format(calendar.time)
    }
}