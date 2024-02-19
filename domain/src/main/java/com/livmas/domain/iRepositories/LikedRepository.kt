package com.livmas.domain.iRepositories

import com.livmas.domain.models.LikedItemModel

interface LikedRepository {

    fun getLikedItems(): List<LikedItemModel>?

    fun deleteItemById(itemId: String)

    fun insertItem(item: LikedItemModel)
}