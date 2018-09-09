package com.diogo.weread

import android.app.Application
import com.diogo.weread.di.interactorModule
import com.diogo.weread.di.networkModule
import com.diogo.weread.di.presenterModule
import com.diogo.weread.di.repositoryModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule

class WeReadApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidModule(this@WeReadApplication))
        import(networkModule)
        import(repositoryModule)
        import(interactorModule)
        import(presenterModule)
    }


}