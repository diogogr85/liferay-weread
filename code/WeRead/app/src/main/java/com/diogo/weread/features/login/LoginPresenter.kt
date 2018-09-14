package com.diogo.weread.features.login

import android.util.Log
import com.diogo.weread.data.models.Auth
import com.diogo.weread.data.models.User
import com.diogo.weread.features.base.BasePresenter


class LoginPresenter(private val interactor: LoginInteractor): BasePresenter<LoginView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun authenticateUser(email: String, password: String) {
        getView()?.showProgress(true)
        val disposable = interactor.authenticateUser(Auth(email, password),
                {
                    //TODO - user signed
                    getView()?.showProgress(false)
                    Log.d("SESSION-TOKEN", it.accessToken)
//                    getView()?.onLoginSuccess()
                    getCurrentUser()
                },
                {
                    getView()?.showMessage(it)
                    getView()?.showProgress(false)
                })

        compositeDisposable.add(disposable)
    }

    private fun getUser(userId: String) {
        try {
            getView()?.showProgress(true)
            val disposable = interactor.getUser(userId,
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

    private fun getCurrentUser() {
        try {
            getView()?.showProgress(true)
            val disposable = interactor.getCurrentUser(
                    {
                        getView()?.onLoginSuccess()
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

    fun isUserLogged(): Boolean {
        val user: User? = interactor.getCachedUser()
        return user != null
    }

}