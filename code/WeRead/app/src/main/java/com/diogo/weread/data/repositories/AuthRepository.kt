package com.diogo.weread.data.repositories

import com.diogo.weread.BuildConfig
import com.diogo.weread.data.models.Auth
import com.diogo.weread.data.models.User
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.auth.TokenAuthorization
import com.wedeploy.android.transport.Response
import io.reactivex.Single


class AuthRepository(private val weDeployClient: WeDeploy) {

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
                .allUsers
                .asSingle()
    }

    fun getUser(userId: String): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .getUser(userId)
                .asSingle()
    }

    /**
     * Call this to verify if the user was previous logged. For instance, at splash screen check
     * for a session in shared preferences if false call this method and then navigate user to
     * login or main screen
     */
    @Throws(IllegalArgumentException::class)
    fun getCurrentUser(accessToken: String): Single<Response> {
        return weDeployClient.auth(BuildConfig.API_AUTH_ENDPOINT)
                .authorization(TokenAuthorization(accessToken))
                .currentUser
                .asSingle()
    }

}
