package com.diogo.weread.data.source.local.database

import android.arch.persistence.room.TypeConverter
import com.diogo.weread.data.models.FeedItem
import com.diogo.weread.utils.fromJson
import com.diogo.weread.utils.toJson


class FeedTypeConverter {

    @TypeConverter
    fun fromFeedItem(feedItem: FeedItem): String {
        return feedItem.toJson()
    }

    @TypeConverter
    fun toFeedItem(feedItemJson: String): FeedItem {
        return feedItemJson.fromJson<FeedItem>()
    }

    @TypeConverter
    fun fromFeedItemList(feedItem: List<FeedItem>): String {
        return feedItem.toJson()
    }

    @TypeConverter
    fun toFeedItemList(feedItemJson: String): List<FeedItem> {
        return feedItemJson.fromJson<List<FeedItem>>()
    }

}
