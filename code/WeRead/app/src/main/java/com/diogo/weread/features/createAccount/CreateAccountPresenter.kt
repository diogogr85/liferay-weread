package com.diogo.weread.features.createAccount

import com.diogo.weread.data.models.User
import com.diogo.weread.features.base.BasePresenter

class CreateAccountPresenter(
        private val interactor: CreateAccountInteractor): BasePresenter<CreateAccountView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun createAccount(name: String, email: String, password: String) {
        getView()?.showProgress(true)
        val disposable = interactor.createAccount(User(name = name, email = email), password,
                {
                    getView()?.onCreateAccountSuccess()
                    getView()?.showProgress(false)
                },
                {
                    getView()?.showProgress(false)
                    getView()?.showMessage(it)
                })

        compositeDisposable.add(disposable)
    }

}