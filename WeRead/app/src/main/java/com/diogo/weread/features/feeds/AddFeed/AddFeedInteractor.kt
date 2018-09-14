package com.diogo.weread.features.feeds.AddFeed

import android.content.SharedPreferences
import android.util.Log
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.User
import com.diogo.weread.data.repositories.FeedMotorRepository
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.data.source.local.get
import com.diogo.weread.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class AddFeedInteractor(private val repository: FeedRepository,
                        private val prefs: SharedPreferences,
                        private val motorRepository: FeedMotorRepository) {

    fun getFeedRss(feedUrl: String, category: String,
                   onSuccess: (Feed) -> Unit,
                   onError: (String?) -> Unit): Disposable {
        return motorRepository.getFeedRss(feedUrl)
                .subscribeOn(Schedulers.io())
                .map {
                    Utils.parseRssToFeed(getCurrentUserId(), feedUrl, category, it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("FEED-RSS", it?.toJson())
                            if (it != null) {
                                onSuccess(it)
                            } else {
                                onError("Não foi possível adicionar o feed")
                            }
                        },
                        {
                            it.printStackTrace()
                            onError(it.message)
                        }
                )
    }

    private fun getCurrentUserId(): String {
        val user = prefs.get(PREFS_WEREAD_CURRENT_USER, User("-1", "", "").toJson()).fromJson<User>()

        return user.id!!
    }

}