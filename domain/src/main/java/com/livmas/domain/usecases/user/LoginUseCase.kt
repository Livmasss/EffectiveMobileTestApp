package com.livmas.domain.usecases.user

import com.livmas.domain.AuthedUserHolder
import com.livmas.domain.models.UserModel

class LoginUseCase(private val user: UserModel): UserUseCase() {
    fun execute() {
        repository.insertAuthedUser(user)
        AuthedUserHolder.holdingAuthedUser = user
    }
}