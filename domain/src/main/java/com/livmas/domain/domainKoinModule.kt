package com.livmas.domain

import com.livmas.domain.models.UserModel
import com.livmas.domain.usecases.user.LogoutUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        LogoutUseCase(get<UserModel>())
    }
}