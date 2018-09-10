package com.diogo.weread.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.diogo.weread.data.models.Feed
import io.reactivex.Flowable


@Dao
interface FeedsDao {

    @Query("SELECT * FROM feeds")
    fun getFeeds(): Flowable<Array<Feed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: Array<Feed>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: Feed)

}