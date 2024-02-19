package com.livmas.domain.usecases.liked

class UnlikeItemUseCase: BaseLikedUseCase() {
    fun execute(itemId: String) {
        repository.deleteItemById(itemId)
    }
}