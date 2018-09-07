package com.diogo.weread.features.base

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V> {

    protected val compositeDisposable = CompositeDisposable()
    var view: WeakReference<V>? = null

    fun bindView(view: V?) {
        this.view = WeakReference<V>(view)

        if(view == null) {
            throw Throwable(
                    message = "This presenter doesn't have a view attached to it",
                    cause = InstantiationException())
        }
    }

    fun unbindView() {
        this.view?.clear()
        this.view = null
    }

    abstract fun unsubscribe()

}