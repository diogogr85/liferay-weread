package com.diogo.weread

import android.app.Application
import com.diogo.weread.di.*
import com.squareup.leakcanary.LeakCanary
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule

class WeReadApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidModule(this@WeReadApplication))
        import(networkModule)
        import(databaseModule)
        import(daoModule)
        import(repositoryModule)
        import(interactorModule)
        import(presenterModule)
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

}