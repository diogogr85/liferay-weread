package com.diogo.weread.data.source.local.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.diogo.weread.data.models.Feed
import io.reactivex.Flowable


@Dao
interface FeedsDao {

    @Query("SELECT * FROM feed")
    fun getFeeds(): Flowable<List<Feed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: List<Feed>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: Feed)

}
