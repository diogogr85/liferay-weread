package com.diogo.weread.data.repositories

import com.diogo.weread.BuildConfig
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.transport.Response
import io.reactivex.Single

class FeedRepository(private val weDeployClient: WeDeploy) {

    fun getFeeds(): Single<Response> {
        return weDeployClient.data(BuildConfig.API_DATA_ENDPOINT)
                .get("feeds")
                .asSingle()
    }

}