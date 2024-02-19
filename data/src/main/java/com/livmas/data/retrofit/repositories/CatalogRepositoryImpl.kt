package com.livmas.data.retrofit.repositories

import com.livmas.data.retrofit.models.ResponseCatalogItem
import com.livmas.data.retrofit.sources.RemoteCatalogDataSource

class CatalogRepositoryImpl {
    companion object {
        private val privateInstance: CatalogRepositoryImpl by lazy {
            CatalogRepositoryImpl()
        }
        val instance = privateInstance
    }

    private val source = RemoteCatalogDataSource()

    fun getAllItems(): List<ResponseCatalogItem>? {
        val res = source.getCatalogItems()
        if (!res.isSuccessful) {
            println(res.message())
            return null
        }

        return res.body()?.items
    }

    fun getItemById(id: String): ResponseCatalogItem? {
        return getAllItems()?.find {
            it.id == id
        }
    }
}