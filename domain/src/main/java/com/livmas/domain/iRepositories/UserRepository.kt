package com.livmas.domain.iRepositories

import com.livmas.domain.models.UserModel

interface UserRepository {
    fun getAuthedUser(): UserModel?

    fun deleteAuthedUser(user: UserModel)

    fun insertAuthedUser(user: UserModel)
}