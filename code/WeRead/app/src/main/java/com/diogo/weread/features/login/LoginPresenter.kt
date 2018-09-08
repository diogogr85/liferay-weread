package com.diogo.weread.features.login

import com.diogo.weread.data.models.Session
import com.diogo.weread.features.base.BasePresenter


class LoginPresenter(private val interactor: LoginInteractor): BasePresenter<LoginView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun loginUser(email: String, password: String) {
        getView()?.showProgress(true)
        val disposable = interactor.authenticateUser(Session(email, password),
                {
                    //TODO - user logged
                    getView()?.showProgress(false)
                },
                {
                    getView()?.showMessage(it)
                    getView()?.showProgress(false)
                })

        compositeDisposable.add(disposable)
    }

}