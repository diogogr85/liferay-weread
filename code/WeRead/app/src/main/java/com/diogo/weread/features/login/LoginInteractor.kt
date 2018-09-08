package com.diogo.weread.features.login

import android.util.Log
import com.diogo.weread.data.models.Session
import com.diogo.weread.data.repositories.AuthRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginInteractor(private val repository: AuthRepository) {

    fun authenticateUser(session: Session,
                         onSuccess: (Any) -> Unit,
                         onError: (String) -> Unit): Disposable {
        return repository.authenticateUser(session)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it)
                            Log.d("AUTHENTICATION-SUCCESS", "User authenticated: ${it.body}")
                        },
                        {
                            it.printStackTrace()
                            Log.d("AUTHENTICATION-ERROR", it.message)
                            onError("Ocorreu uma falha. ${it.message}")
                        }

                )
    }

}