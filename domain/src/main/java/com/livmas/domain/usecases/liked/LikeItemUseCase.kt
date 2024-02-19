package com.livmas.domain.usecases.liked

import com.livmas.domain.models.LikedItemModel

class LikeItemUseCase: BaseLikedUseCase() {
    fun execute(itemModel: LikedItemModel) {
        repository.insertItem(itemModel)
    }
}