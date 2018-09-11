package com.diogo.weread.features.feeds.AddFeed

import android.content.SharedPreferences
import android.util.Log
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.repositories.FeedMotorRepository
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.data.source.local.get
import com.diogo.weread.utils.PREFS_WEREAD_CURRENT_USER_ID
import com.diogo.weread.utils.Utils
import com.diogo.weread.utils.getBody
import com.diogo.weread.utils.toJson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class AddFeedInteractor(private val repository: FeedRepository,
                        private val prefs: SharedPreferences,
                        private val motorRepository: FeedMotorRepository) {

    fun getFeedRss(feedUrl: String, category: String,
                   onSuccess: (Feed) -> Unit,
                   onError: (String?) -> Unit,
                   onComplete: () -> Unit): Disposable {
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
                        },
                        {
                            onComplete()
                        }
                )
    }

//    fun saveFeed(feed: Feed,
//                 onSuccess: () -> Unit,
//                 onError: (String) -> Unit): Disposable {
//        return repository.saveFeedRemote(feed)
//                .subscribeOn(Schedulers.io())
//                .map {
//                    repository.saveFeedLocal(it.getBody<Feed>())
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        {
//                            onSuccess()
//                        },
//                        {
//                            it.printStackTrace()
//                            onError("Ocorreu uma falha. ${it.message}")
//                        }
//                )
//
//    }

    private fun getCurrentUserId(): String {
        return prefs.get(PREFS_WEREAD_CURRENT_USER_ID, "-1")
    }

}