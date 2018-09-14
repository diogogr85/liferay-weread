package com.diogo.weread.features.splash

import com.diogo.weread.data.models.User
import com.diogo.weread.features.base.BasePresenter

class SplashPresenter(private val interactor: SplashInteractor): BasePresenter<SplashView>() {

    override fun unsubscribe() {
    }

    fun isUserLogged(): Boolean {
        val user: User? = interactor.getCachedUser()
        return user != null
    }

}