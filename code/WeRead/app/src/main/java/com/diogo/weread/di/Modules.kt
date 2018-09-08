package com.diogo.weread.di

import com.diogo.weread.data.repositories.AuthRepository
import com.diogo.weread.features.createAccount.CreateAccountPresenter
import com.diogo.weread.features.login.LoginInteractor
import com.diogo.weread.features.login.LoginPresenter
import com.wedeploy.android.WeDeploy
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val networkModule = Kodein.Module("Network") {
    bind<WeDeploy>() with singleton { WeDeploy.Builder().build() }
}

val repositoryModule = Kodein.Module("Repository") {
    bind<AuthRepository>() with provider { AuthRepository(instance()) }
}

val interactorModule = Kodein.Module("Interactor") {
    bind<LoginInteractor>() with provider { LoginInteractor(instance()) }
}

val presenterModule = Kodein.Module("Presenters") {
    bind<LoginPresenter>() with provider { LoginPresenter(instance()) }
    bind<CreateAccountPresenter>() with provider { CreateAccountPresenter() }
}