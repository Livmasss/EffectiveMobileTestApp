package com.livmas.data.localDataBase.repositories

import com.livmas.data.localDataBase.daos.LikedDao
import com.livmas.data.localDataBase.entities.LikedEntity
import com.livmas.data.retrofit.repositories.CatalogRepository
import com.livmas.domain.iRepositories.LikedRepository
import com.livmas.domain.models.LikedItemModel
import java.util.UUID

class LikedRepositoryImpl(private val dao: LikedDao, private val remoteRepository: CatalogRepository): LikedRepository {
    override fun getLikedItems(): List<LikedItemModel>? {
        val liked = dao.getAll().map {
            it.remoteItemId
        }
        val all = remoteRepository.getAllItems()?.map {
            LikedItemModel(it.id, listOf(), it.price.priceWithDiscount.toInt(),
                it.price.priceWithDiscount.toInt(), it.price.unit[0],
                it.price.discount, it.title,
                it.subtitle, it.feedback.rating, it.feedback.count, true)
        }

        val result = all?.mapNotNull { item ->
            if (item.id !in liked)
                null
            else
                item
        }

        return result
    }

    override fun deleteItemById(itemId: String) {
        dao.delete(itemId)
    }

    override fun insertItem(item: LikedItemModel) {
        dao.insert(LikedEntity(item))
    }
}