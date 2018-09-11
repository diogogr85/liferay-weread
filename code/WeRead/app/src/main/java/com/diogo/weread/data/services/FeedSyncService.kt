package com.diogo.weread.data.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.utils.getBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class FeedSyncService: IntentService("FeedSyncService"), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val repository: FeedRepository by instance()
    protected val compositeDisposable = CompositeDisposable()

    override fun onHandleIntent(intent: Intent?) {
        val disposable = repository.getFeedsRemote()
                .subscribeOn(Schedulers.io())
                .map {
                    repository.saveFeedsLocal(it.getBody<List<Feed>>())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("SYNC-SERVICE-SUCCESS", "Feeds synced")
                            compositeDisposable.clear()
                        },
                        {
                            it.printStackTrace()
                            Log.d("SYNC-SERVICE-ERROR", "Feeds sync error")
                            compositeDisposable.clear()
                        }
                )
        compositeDisposable.add(disposable)
    }

}