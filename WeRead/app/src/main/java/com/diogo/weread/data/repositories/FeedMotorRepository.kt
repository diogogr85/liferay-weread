package com.diogo.weread.data.repositories

import com.diogo.weread.data.models.Rss
import com.diogo.weread.data.source.remote.RestApi
import io.reactivex.Observable


class FeedMotorRepository(private val apiClient: RestApi) {

    fun getFeedRss(rssUrl: String): Observable<Rss> {
        return apiClient.getFeed(rssUrl)
    }

}