package com.diogo.weread.features.feedDetails

import com.diogo.weread.features.base.BasePresenter

class FeedDetailsPresenter: BasePresenter<FeedDetailsView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

}