package com.livmas.domain.usecases.liked

import com.livmas.domain.models.LikedItemModel

class GetLikedItemUseCase: BaseLikedUseCase() {
    fun execute() {
        repository.getLikedItems()
    }
}