package com.diogo.weread.data.repositories

import android.content.SharedPreferences
import com.diogo.weread.BuildConfig
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.source.local.SharedPrefsManager
import com.diogo.weread.data.source.local.database.FeedsDao
import com.diogo.weread.utils.toJsonObject
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.auth.Authorization
import com.wedeploy.android.auth.TokenAuthorization
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

    fun getFeedsLocal(): Flowable<List<Feed>> {
        return dao.getFeeds()
    }

    fun saveFeedsLocal(feeds: List<Feed>): Completable {
        return Completable.create { emitter ->
            try {
                dao.insertFeed(feeds)
                emitter.onComplete()
            } catch (e: RuntimeException) {
                emitter.onError(e)
            }
        }
    }

    fun saveFeedLocal(feed: Feed): Completable {
        return Completable.create { emitter ->
            try {
                dao.insertFeed(feed)
                emitter.onComplete()
            } catch (e: RuntimeException) {
                emitter.onError(e)
            }
        }
    }

    fun saveFeedRemote(feed: Feed): Single<Response> {
        return weDeployClient.data(BuildConfig.API_DATA_ENDPOINT)
                .create("feeds", feed.toJsonObject())
                .asSingle()
    }


}