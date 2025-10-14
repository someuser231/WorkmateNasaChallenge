package com.knc.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dummy_json_products")
data class DjsnProductDbItem(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "price")
    val price: Float?,

    @ColumnInfo(name = "images")
    val images: String?
)
