package com.knc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.knc.domain.models.ApodModel
import com.knc.domain.models.DjsnProductModel
import kotlin.Int

@Database(entities = [DjsnProductDbItem::class, ApodDbItem::class], version = 1)
abstract class MainDb: RoomDatabase() {
    abstract fun getDjsnDao(): DjsnDao
    abstract fun getApodDao(): ApodDao

    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "data.db"
            ).allowMainThreadQueries().build()
        }
    }

    fun modelToDbItemDjsn(model: DjsnProductModel): DjsnProductDbItem{
        return DjsnProductDbItem(
            id = model.id,
            title = model.title,
            description = model.description,
            price = model.price,
            images = model.images[0]
        )
    }
    fun dbItemToModelDjsn(item: DjsnProductDbItem): DjsnProductModel{
        return DjsnProductModel(
            id = item.id,
            title = item.title,
            description = item.description,
            price = item.price,
            images = arrayListOf(item.images)
        )
    }

    fun modelToDbItemApod(model: ApodModel): ApodDbItem{
        return ApodDbItem(
            id = model.id,
            date = model.date,
            explanation = model.explanation,
            hdurl = model.hdurl,
            media_type = model.media_type,
            service_version = model.service_version,
            title = model.title,
            url = model.url
        )
    }
    fun dbItemToModelApod(item: ApodDbItem): ApodModel{
        return ApodModel(
            id = item.id,
            date = item.date,
            explanation = item.explanation,
            hdurl = item.hdurl,
            media_type = item.media_type,
            service_version = item.service_version,
            title = item.title,
            url = item.url
        )
    }

}