package com.diogo.weread.data.repositories

import com.diogo.weread.BuildConfig
import com.diogo.weread.data.models.Session
import com.diogo.weread.data.models.User
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.transport.Response
import io.reactivex.Single


class AuthRepository(private val weDeployClient: WeDeploy) {

    fun authenticateUser(session: Session): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .signIn(session.email, session.password)
                .asSingle()
    }

    fun createAccount(user: User, password: String): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .createUser(user.email, password, user.name)
                .asSingle()
    }



    /**
     * Call this to verify if the user was previous logged. For instance, at splash screen check
     * for a session in shared preferences if false call this method and then navigate user to
     * login or main screen
     */
    @Throws(IllegalArgumentException::class)
    fun getCurrentUser(): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .currentUser
                .asSingle()
    }

}
