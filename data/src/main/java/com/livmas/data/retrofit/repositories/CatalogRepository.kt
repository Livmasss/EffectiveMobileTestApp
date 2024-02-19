package com.livmas.data.retrofit.repositories

import com.livmas.data.retrofit.models.ResponseCatalogItem
import com.livmas.data.retrofit.sources.RemoteCatalogDataSource

class CatalogRepository {
    companion object {
        private val privateInstance: CatalogRepository by lazy {
            CatalogRepository()
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