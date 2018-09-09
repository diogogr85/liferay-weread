package com.diogo.weread.features.feeds

import com.diogo.weread.features.base.BasePresenter

class FeedsPresenter(private val interactor: FeedsInteractor): BasePresenter<FeedsView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getFeeds() {
        getView()?.showProgress(true)
        interactor.getFeeds(
                {
                    getView()?.showProgress(false)
                    getView()?.onFeedsSuccess(it)
                },
                {
                    getView()?.showProgress(false)
                    getView()?.showMessage(it)
                }
        )
    }

}