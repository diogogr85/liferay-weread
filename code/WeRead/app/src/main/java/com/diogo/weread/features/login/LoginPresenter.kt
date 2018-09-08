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
                    //TODO - user signed
//                    getView()?.showProgress(false)
                    getCurrentUser()
                },
                {
                    getView()?.showMessage(it)
                    getView()?.showProgress(false)
                })

        compositeDisposable.add(disposable)
    }

    private fun getCurrentUser() {
        try {
            getView()?.showProgress(true)
            val disposable = interactor.getCurrentUser(
                    {
                        //TODO - user logged
                        getView()?.showProgress(false)
                    },
                    {
                        getView()?.showMessage(it)
                        getView()?.showProgress(false)
                    })

            compositeDisposable.add(disposable)

        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            getView()?.showMessage(e.message)
        }finally {
            getView()?.showProgress(false)
        }
    }

}