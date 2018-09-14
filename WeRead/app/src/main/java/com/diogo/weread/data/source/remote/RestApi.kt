package com.diogo.weread.data.source.remote

import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.Rss
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RestApi {

    @GET
    fun getFeed(@Url feedUrl: String): Observable<Rss>

}