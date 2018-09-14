package com.diogo.weread.features.feeds

import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.utils.fromBody
import io.reactivex.Observable
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
                            onError("${it.message}")
                        },
                        {
                            onComplete()
                        }
                )
    }

    fun getFeedsRemote(onSuccess: (List<Feed>) -> Unit,
                       onError: (String) -> Unit): Disposable {
        return repository.getFeedsRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it.fromBody<Array<Feed>>().asList())
                        },
                        {
                            it.printStackTrace()
                            onError("${it.message}")
                        }
                )
    }

    fun saveFeedLocal(feed: Feed,
                      onSuccess: () -> Unit,
                      onError: () -> Unit): Disposable {
        return Observable.just(feed)
                .subscribeOn(Schedulers.io())
                .map {
                    repository.saveFeedLocal(feed)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess()
                        },
                        {
                            it.printStackTrace()
                            onError()
                        }
                )
    }

    fun saveFeedRemote(feed: Feed,
                      onSuccess: () -> Unit,
                      onError: (String) -> Unit): Disposable {
        return repository.saveFeedRemote(feed)
                .subscribeOn(Schedulers.io())
                .map {
                    repository.saveFeedLocal(it.fromBody<Feed>())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess()
                        },
                        {
                            it.printStackTrace()
                            onError("${it.message}")
                        }
                )
    }

}