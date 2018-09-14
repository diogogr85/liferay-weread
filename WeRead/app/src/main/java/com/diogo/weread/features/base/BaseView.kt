package com.diogo.weread.features.base

interface BaseView {
    fun showProgress(show: Boolean)
    fun showMessage(message: String?)
}