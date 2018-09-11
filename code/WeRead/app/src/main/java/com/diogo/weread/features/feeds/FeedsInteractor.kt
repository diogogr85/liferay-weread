package com.diogo.weread.features.feeds

import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.utils.getBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FeedsInteractor(private val repository: FeedRepository) {

    fun getFeeds(onSuccess: (List<Feed>) -> Unit,
                 onError: (String) -> Unit,
                 onComplete: () -> Unit): Disposable {
        return repository.getFeedsLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it)
                        },
                        {
                            it.printStackTrace()
                            onError("Ocorreu uma falha. ${it.message}")
                        },
                        {
                            onComplete()
                        }
                )
    }

    fun saveFeed(feed: Feed,
                 onSuccess: () -> Unit,
                 onError: (String) -> Unit): Disposable {
        return repository.saveFeedRemote(feed)
                .subscribeOn(Schedulers.io())
                .map {
                    repository.saveFeedLocal(it.getBody<Feed>())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess()
                        },
                        {
                            it.printStackTrace()
                            onError("Ocorreu uma falha. ${it.message}")
                        }
                )

    }

}