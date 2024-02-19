package com.livmas.domain.usecases.liked

import com.livmas.domain.models.LikedItemModel

class GetLikedItemsUseCase: BaseLikedUseCase() {
    fun execute(): List<LikedItemModel>? {
        return repository.getLikedItems()
    }
}