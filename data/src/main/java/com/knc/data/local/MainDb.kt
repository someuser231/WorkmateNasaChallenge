package com.knc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.knc.domain.models.DjsnProductModel

@Database(entities = [DjsnProductDbItem::class], version = 1)
abstract class MainDb: RoomDatabase() {
    abstract fun getDjsnDao(): DjsnDao

    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "data.db"
            ).allowMainThreadQueries().build()
        }
    }

    fun modelToDbItem(model: DjsnProductModel): DjsnProductDbItem{
        return DjsnProductDbItem(
            id = model.id,
            title = model.title,
            description = model.description,
            price = model.price,
            images = model.images[0]
        )
    }

    fun dbItemToModel(item: DjsnProductDbItem): DjsnProductModel{
        return DjsnProductModel(
            id = item.id,
            title = item.title,
            description = item.description,
            price = item.price,
            images = arrayListOf(item.images)
        )
    }

}