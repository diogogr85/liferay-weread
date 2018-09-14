package com.diogo.weread.data.source.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.FeedItem


@Database(entities = [Feed::class, FeedItem::class],
        version = 1,
        exportSchema = false)
@TypeConverters(FeedTypeConverter::class)
abstract class WeReadDataBase: RoomDatabase() {

    abstract fun feedsDao(): FeedsDao


}