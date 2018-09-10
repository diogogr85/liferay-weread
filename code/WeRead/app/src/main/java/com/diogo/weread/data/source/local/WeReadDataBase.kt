package com.diogo.weread.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.diogo.weread.data.models.Feed

@Database(entities = arrayOf(Feed::class), version = 1)
abstract class WeReadDataBase: RoomDatabase() {

    abstract fun feedsDao(): FeedsDao

}