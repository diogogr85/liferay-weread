package com.diogo.weread.data.repositories

import com.diogo.weread.BuildConfig
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.source.local.FeedsDao
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.transport.Response
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


class FeedRepository(private val weDeployClient: WeDeploy,
                     private val dao: FeedsDao) {

    fun getFeedsRemote(): Single<Response> {
        return weDeployClient.data(BuildConfig.API_DATA_ENDPOINT)
                .get("feeds")
                .asSingle()
    }

    fun getFeedsLocal(): Flowable<Array<Feed>> {
        return dao.getFeeds()
    }

    fun persistFeeds(feeds: Array<Feed>): Completable {
        return Completable.create { emitter ->
            try {
                dao.insertFeed(feeds)
                emitter.onComplete()
            } catch (e: RuntimeException) {
                emitter.onError(e)
            }
        }
    }

    fun saveFeed(feed: Feed): Completable {
        return Completable.create { emitter ->
            try {
                dao.insertFeed(feed)
                emitter.onComplete()
            } catch (e: RuntimeException) {
                emitter.onError(e)
            }
        }
    }

}