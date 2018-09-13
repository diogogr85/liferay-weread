package com.diogo.weread.features.login

import android.content.SharedPreferences
import android.util.Log
import com.diogo.weread.data.models.Auth
import com.diogo.weread.data.models.Session
import com.diogo.weread.data.models.User
import com.diogo.weread.data.repositories.AuthRepository
import com.diogo.weread.data.source.local.put
import com.diogo.weread.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginInteractor(private val repository: AuthRepository,
                      private val prefs: SharedPreferences) {

    fun authenticateUser(auth: Auth,
                         onSuccess: (Session) -> Unit,
                         onError: (String) -> Unit): Disposable {
        return repository.authenticateUser(auth)
                .subscribeOn(Schedulers.io())
                .map {
                    val session = it.getBody<Session>()
                    cacheSession(session)
                    session
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it)
                            Log.d("AUTHENTICATION-SUCCESS", "User authenticated: ${it.toJson()}")
                        },
                        {
                            it.printStackTrace()
                            Log.d("AUTHENTICATION-ERROR", it.message)
                            val errorMsg: String = if (it.message != null && !it.message.equals("")) {
                                it.message!!
                            } else {
                                "A network error has occurred"
                            }
                            onError(errorMsg)
                        }
                )
    }

    @Throws(IllegalArgumentException::class)
    fun getCurrentUser(accessToKen: String,
                       onSuccess: (User) -> Unit,
                       onError: (String) -> Unit): Disposable {
        return repository.getCurrentUser(accessToKen)
                .subscribeOn(Schedulers.io())
                .map {
                    val user = it.getBody<User>()
                    cacheUser(user)
                    user
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it)
                            Log.d("CURRENT-USER-SUCCESS", "User authenticated: ${it.toJson()}")
                        },
                        {
                            it.printStackTrace()
                            Log.d("USER-ERROR", it.message)
                            onError("Ocorreu uma falha. ${it.message}")
                        }
                )
    }

    fun getUser(userId: String,
                onSuccess: (User) -> Unit,
                onError: (String) -> Unit): Disposable {
        return repository.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it.getBody<User>())
                            Log.d("USER-SUCCESS", "User authenticated: ${it.body}")
                        },
                        {
                            it.printStackTrace()
                            Log.d("USER-ERROR", it.message)
                            onError("Ocorreu uma falha. ${it.message}")
                        }
                )
    }

    fun cacheUser(user: User) {
        prefs.put(PREFS_WEREAD_CURRENT_USER, user.toJson())
    }

    fun cacheSession(session: Session) {
        prefs.put(PREFS_WEREAD_SESSION, session.toJson())
        prefs.put(PREFS_WEREAD_ACCESS_TOKEN, session.accessToken)
    }

    fun getCachedUser(): User? {
        return prefs.getString(PREFS_WEREAD_CURRENT_USER, "").fromJson<User>()
    }

}