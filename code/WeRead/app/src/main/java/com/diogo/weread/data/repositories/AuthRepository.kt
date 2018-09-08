package com.diogo.weread.data.repositories

import android.util.Log
import com.diogo.weread.BuildConfig
import com.diogo.weread.data.models.Session
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.transport.Response
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class AuthRepository(private val weDeployClient: WeDeploy) {

    fun authenticateUser(session: Session): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .signIn(session.email, session.password)
                .asSingle()
    }


}
