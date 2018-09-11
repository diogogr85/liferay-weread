package com.diogo.weread.features.feeds

import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.feeds.AddFeed.AddFeedInteractor
import com.diogo.weread.features.base.BasePresenter


class FeedsPresenter(private val interactor: FeedsInteractor,
                     private val addInteractor: AddFeedInteractor): BasePresenter<FeedsView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getFeeds() {
        getView()?.showProgress(true)
        val disposable = interactor.getFeeds(
                {
                    getView()?.showProgress(false)
                    getView()?.onFeedsSuccess(it)
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

    fun addFeedRss(rssUrl: String, category: String) {
        getView()?.showProgress(true)
        val disposable = addInteractor.getFeedRss(rssUrl, category,
                { feed ->
                    getView()?.showProgress(false)
                    getView()?.onAddFeedsSuccess(feed)

                    //Start sync
                    saveFeed(feed)
                },
                {
                    getView()?.showProgress(false)
                    getView()?.showMessage(it)
                },
                {
//                    unsubscribe()
                })

        compositeDisposable.add(disposable)
    }

    private fun saveFeed(feed: Feed) {
        val disposable = interactor.saveFeed(feed,
                {
                    getView()?.showMessage("Sync complete")
                    unsubscribe()
                },
                {
                    getView()?.showMessage("Sync failed")
                    unsubscribe()
                })

        compositeDisposable.add(disposable)
    }

}