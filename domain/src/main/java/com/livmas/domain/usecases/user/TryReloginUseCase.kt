package com.livmas.domain.usecases.user

import com.livmas.domain.AuthedUserHolder

class TryReloginUseCase: UserUseCase() {
    fun execute(): Boolean {
        val user = repository.getAuthedUser()
        AuthedUserHolder.holdingAuthedUser = user
        return user != null
    }
}