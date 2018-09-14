package com.diogo.weread.features.splash

import com.diogo.weread.data.models.User
import com.diogo.weread.data.repositories.AuthRepository


class SplashInteractor(private val repository: AuthRepository) {

    fun getCachedUser(): User? {
        return repository.getCachedUser()
    }

}