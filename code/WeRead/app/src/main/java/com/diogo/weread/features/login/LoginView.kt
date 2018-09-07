package com.diogo.weread.features.login

import com.diogo.weread.features.base.BaseView

interface LoginView: BaseView {
    fun onLoginSuccess()
    fun onError()
}