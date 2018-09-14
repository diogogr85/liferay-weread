package com.diogo.weread.data.repositories

import com.diogo.weread.BuildConfig
import com.diogo.weread.data.models.Auth
import com.diogo.weread.data.models.Session
import com.diogo.weread.data.models.User
import com.diogo.weread.data.source.local.SharedPrefsManager
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.auth.TokenAuthorization
import com.wedeploy.android.transport.Response
import io.reactivex.Single


class AuthRepository(private val weDeployClient: WeDeploy,
                     private val prefsManager: SharedPrefsManager) {

    fun authenticateUser(auth: Auth): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .signIn(auth.email, auth.password)
                .asSingle()
    }

    fun createAccount(user: User, password: String): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .createUser(user.email, password, user.name)
                .asSingle()
    }

    fun getUsers(): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .authorization(TokenAuthorization(prefsManager.getAccessToken()))
                .allUsers
                .asSingle()
    }

    fun getUser(userId: String): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .authorization(TokenAuthorization(prefsManager.getAccessToken()))
                .getUser(userId)
                .asSingle()
    }

    fun getCurrentUser(): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .authorization(TokenAuthorization(prefsManager.getAccessToken()))
                .currentUser
                .asSingle()
    }

    fun cacheSession(session: Session) {
        prefsManager.cacheSession(session)
    }

    fun cacheUser(user: User) {
        prefsManager.cacheUser(user)
    }

    fun getCachedUser(): User? {
        return prefsManager.getCachedUser()
    }

}
