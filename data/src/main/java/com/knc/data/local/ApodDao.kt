package com.knc.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.knc.domain.models.ApodModel

@Dao
interface ApodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApod(item: ApodDbItem)

    @Query("SELECT * FROM nasa_apod WHERE date BETWEEN :startDate AND :endDate")
    fun getApods(startDate: String, endDate: String): List<ApodDbItem>
}