package com.knc.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.knc.domain.models.DjsnProductModel

@Dao
interface DjsnDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(item: DjsnProductDbItem)

    @Query("select * from dummy_json_products where id = :id")
    fun getProduct(id: Int): DjsnProductDbItem
}