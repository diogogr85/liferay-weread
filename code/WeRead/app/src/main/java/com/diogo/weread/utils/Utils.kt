package com.diogo.weread.utils

import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.FeedItem
import com.diogo.weread.data.models.Rss
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class Utils {

    companion object {

        fun parseRssToFeed(userId: String, rssUrl: String, category: String, rss: Rss): Feed? {
            if (rss.channel != null) {
                with(rss.channel!!) {
                    val feedItems = ArrayList<FeedItem>()

                    //Assemble feed items
                    if (items != null) {
                        for (item in items!!) {
                            feedItems.add(FeedItem(title = item.title,
                                    description = item.description, url = item.link))
                        }
                    }

                    //Assemble feed
                    val feed = Feed(title = title ?: "Feed title", description = description,
                            feedImageUrl = channelIcon?.url, category = category,
                            url = rssUrl, userId = userId, feedItem = feedItems)

                    return feed
                }
            }

            return null
        }

    }

}
