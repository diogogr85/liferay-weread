package com.diogo.weread.features.login

import com.diogo.weread.features.base.BasePresenter


class LoginPresenter: BasePresenter<LoginView>() {

    override fun unsubscribe() {
    }

    fun loginUser(email: String, password: String) {
        //TODO - log in user
    }

}