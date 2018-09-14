package com.diogo.weread.features.createAccount

import android.util.Log
import com.diogo.weread.data.models.User
import com.diogo.weread.data.repositories.AuthRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreateAccountInteractor(private val repository: AuthRepository) {

    fun createAccount(user: User, password: String,
                      onSuccess: (Any) -> Unit,
                      onError: (String) -> Unit): Disposable {
        return repository.createAccount(user, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onSuccess(it)
                            Log.d("CREATE-ACCOUNT-SUCCESS", "User created: ${it.body}")
                        },
                        {
                            it.printStackTrace()
                            Log.d("CREATE-ACCOUNT-ERROR", it.message)
                            onError("Ocorreu uma falha. ${it.message}")
                        }
                )
    }

}