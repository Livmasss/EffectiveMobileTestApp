package com.livmas.domain.usecases.liked

import com.livmas.domain.iRepositories.LikedRepository
import org.koin.java.KoinJavaComponent.inject

abstract class BaseLikedUseCase {
    protected val repository: LikedRepository by inject(LikedRepository::class.java)
}