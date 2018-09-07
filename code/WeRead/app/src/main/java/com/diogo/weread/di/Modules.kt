package com.diogo.weread.di

import com.diogo.weread.features.login.LoginPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider


val presenterModule = Kodein.Module("Presenters") {
    bind<LoginPresenter>() with provider { LoginPresenter() }
}