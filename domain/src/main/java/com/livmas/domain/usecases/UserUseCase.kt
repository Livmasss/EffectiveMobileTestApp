package com.livmas.domain.usecases

import com.livmas.domain.iRepositories.UserRepository
import org.koin.java.KoinJavaComponent.inject

abstract class UserUseCase {
    protected val repository: UserRepository by inject(UserRepository::class.java)

}