package com.diogo.weread.features.splash

import android.content.Intent
import android.os.Handler
import com.diogo.weread.R
import com.diogo.weread.data.services.FeedSyncService
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.features.feeds.FeedsActivity
import com.diogo.weread.features.login.LoginActivity
import org.kodein.di.generic.instance

private const val SPLASH_TIMER: Long = 2000

class SplashActivity: BaseActivity<SplashView>(), SplashView {

    override val layoutId: Int = R.layout.activity_splash

    override val presenter: SplashPresenter by instance()

    override fun setPresenter() {
        presenter.bindView(this)
    }

    override fun onCreate() {
        startService(Intent(applicationContext, FeedSyncService::class.java))

        Handler().postDelayed({
            val intent: Intent
            if (presenter.isUserLogged()) {
                intent = Intent(applicationContext, FeedsActivity::class.java)
            } else {
                intent = Intent(applicationContext, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, SPLASH_TIMER)
    }

}