package com.diogo.weread.features.feeds

import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.utils.getBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FeedsInteractor(private val repository: FeedRepository) {

    fun getFeeds(onSuccess: (Array<Feed>) -> Unit,
                 onError: (String) -> Unit): Disposable {
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
                        }
                )
    }

}