package com.diogo.weread.features.feeds

import com.diogo.weread.features.base.BasePresenter

class FeedsPresenter(private val interactor: FeedsInteractor): BasePresenter<FeedsView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getFeeds() {
        unsubscribe()

        getView()?.showProgress(true)
        val disposable = interactor.getFeeds(
                {
                    getView()?.showProgress(false)
                    getView()?.onFeedsSuccess(it)
                    compositeDisposable.clear()
                },
                {
                    getView()?.showProgress(false)
                    getView()?.showMessage(it)
                    compositeDisposable.clear()
                }
        )

        compositeDisposable.add(disposable)
    }

}