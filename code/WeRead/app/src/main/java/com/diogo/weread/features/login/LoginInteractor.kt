package com.diogo.weread.features.login

import android.util.Log
import com.diogo.weread.data.models.Auth
import com.diogo.weread.data.models.Session
import com.diogo.weread.data.models.User
import com.diogo.weread.data.repositories.AuthRepository
import com.diogo.weread.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginInteractor(private val repository: AuthRepository) {

    fun authenticateUser(auth: Auth,
                         onSuccess: (Session) -> Unit,
                         onError: (String) -> Unit): Disposable {
        return repository.authenticateUser(auth)
                .subscribeOn(Schedulers.io())
                .map {
                    val session = it.fromBody<Session>()
                    repository.cacheSession(session)
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
    fun getCurrentUser(onSuccess: (User) -> Unit,
                       onError: (String) -> Unit): Disposable {
        return repository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .map {
                    val user = it.fromBody<User>()
                    repository.cacheUser(user)
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
                            onSuccess(it.fromBody<User>())
                            Log.d("USER-SUCCESS", "User authenticated: ${it.body}")
                        },
                        {
                            it.printStackTrace()
                            Log.d("USER-ERROR", it.message)
                            onError("Ocorreu uma falha. ${it.message}")
                        }
                )
    }

    fun getCachedUser(): User? {
        return repository.getCachedUser()
    }

}