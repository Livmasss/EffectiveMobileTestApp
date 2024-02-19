package com.livmas.domain.usecases.user

import com.livmas.domain.AuthedUserHolder
import com.livmas.domain.models.UserModel

class LogoutUseCase(private val user: UserModel?): UserUseCase() {
    fun execute() {
        user?.let { repository.deleteAuthedUser(it) }
        AuthedUserHolder.holdingAuthedUser = null
    }
}