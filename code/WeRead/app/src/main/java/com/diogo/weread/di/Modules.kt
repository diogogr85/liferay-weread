package com.diogo.weread.di

import android.arch.persistence.room.Room
import com.diogo.weread.data.repositories.AuthRepository
import com.diogo.weread.data.repositories.FeedMotorRepository
import com.diogo.weread.data.repositories.FeedRepository
import com.diogo.weread.data.source.local.SharedPrefsManager
import com.diogo.weread.data.source.local.database.FeedsDao
import com.diogo.weread.data.source.local.database.WeReadDataBase
import com.diogo.weread.data.source.remote.RestApi
import com.diogo.weread.data.source.remote.ServiceClient
import com.diogo.weread.features.feeds.AddFeed.AddFeedInteractor
import com.diogo.weread.features.createAccount.CreateAccountInteractor
import com.diogo.weread.features.createAccount.CreateAccountPresenter
import com.diogo.weread.features.feedDetails.FeedDetailsPresenter
import com.diogo.weread.features.feeds.FeedsInteractor
import com.diogo.weread.features.feeds.FeedsPresenter
import com.diogo.weread.features.login.LoginInteractor
import com.diogo.weread.features.login.LoginPresenter
import com.diogo.weread.features.splash.SplashInteractor
import com.diogo.weread.features.splash.SplashPresenter
import com.wedeploy.android.WeDeploy
import org.kodein.di.Kodein
import org.kodein.di.generic.*


val networkModule = Kodein.Module("Network") {
    bind<WeDeploy>() with singleton { WeDeploy.Builder().build() }
    bind<RestApi>() with singleton { ServiceClient().getApiClient() }
}

val databaseModule = Kodein.Module("Database") {
    bind<WeReadDataBase>() with singleton {
        Room.databaseBuilder(instance(),
                WeReadDataBase::class.java,
                "weread.db")
                .build()
    }
}

val daoModule = Kodein.Module("Dao") {
    bind<FeedsDao>() with provider {
        val db: WeReadDataBase = instance()
        db.feedsDao()
    }
    bind<SharedPrefsManager>() with provider { SharedPrefsManager(instance()) }
}

val repositoryModule = Kodein.Module("Repository") {
    bind<AuthRepository>() with provider { AuthRepository(instance(), instance()) }
    bind<FeedRepository>() with provider { FeedRepository(instance(), instance()) }
    bind<FeedMotorRepository>() with provider { FeedMotorRepository(instance()) }
}

val interactorModule = Kodein.Module("Interactor") {
    bind<LoginInteractor>() with provider { LoginInteractor(instance()) }
    bind<CreateAccountInteractor>() with provider { CreateAccountInteractor(instance()) }
    bind<FeedsInteractor>() with provider { FeedsInteractor(instance()) }
    bind<AddFeedInteractor>() with provider { AddFeedInteractor(instance(), instance(), instance()) }
    bind<SplashInteractor>() with provider { SplashInteractor(instance()) }
}

val presenterModule = Kodein.Module("Presenters") {
    bind<LoginPresenter>() with provider { LoginPresenter(instance()) }
    bind<CreateAccountPresenter>() with provider { CreateAccountPresenter(instance()) }
    bind<FeedsPresenter>() with provider { FeedsPresenter(instance(), instance()) }
    bind<FeedDetailsPresenter>() with provider { FeedDetailsPresenter() }
    bind<SplashPresenter>() with provider { SplashPresenter(instance()) }
}
