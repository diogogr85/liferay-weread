package com.diogo.weread.features.feeds

import android.util.Log
import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.feeds.AddFeed.AddFeedInteractor
import com.diogo.weread.features.base.BasePresenter


class FeedsPresenter(private val interactor: FeedsInteractor,
                     private val addInteractor: AddFeedInteractor): BasePresenter<FeedsView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getFeeds(forceUpdate: Boolean) {
        if (forceUpdate) {
            getView()?.showProgress(true)
            val disposable = interactor.getFeeds(
                    {
                        if (!it.isEmpty()) {
                            getView()?.showProgress(false)
                            getView()?.onFeedsSuccess(it)
                        } else {
                            getFeedsRemote()
                        }
                    },
                    {
                        getView()?.showProgress(false)
                        getView()?.onFeedsError()
                    },
                    {
                        unsubscribe()
                    }
            )

            compositeDisposable.add(disposable)
        }
    }

    fun getFeedsRemote() {
        val disposable = interactor.getFeedsRemote(
                {
                    getView()?.showProgress(false)
                    getView()?.onFeedsSuccess(it)
                },
                {
                    getView()?.showProgress(false)
                    getView()?.onFeedsError()
                }
        )

        compositeDisposable.add(disposable)
    }

    fun addFeedRss(rssUrl: String, category: String) {
        getView()?.showProgress(true)
        val disposable = addInteractor.getFeedRss(rssUrl, category,
                { feed ->
                    getView()?.showProgress(false)
                    getView()?.onAddFeedsSuccess(feed)

                    //Start sync
                    saveFeedLocal(feed)
                    saveFeedRemote(feed)
                },
                {
                    getView()?.showProgress(false)
                    getView()?.showMessage(it)
                })

        compositeDisposable.add(disposable)
    }

    private fun saveFeedLocal(feed: Feed) {
        val disposable = interactor.saveFeedLocal(feed,
                {
                    Log.d("SAVE-LOCAL", "Saved")
                },
                {
                    Log.d("SAVE-LOCAL", "Failed")
                }
        )

        compositeDisposable.add(disposable)
    }

    private fun saveFeedRemote(feed: Feed) {
        val disposable = interactor.saveFeedRemote(feed,
                {
                    getView()?.showMessage("Sync complete")
                },
                {
                    getView()?.showMessage("Sync failed")
                })

        compositeDisposable.add(disposable)
    }

}