package com.livmas.data.repositories

import com.livmas.data.models.ResponseCatalogItem
import com.livmas.data.sources.RemoteCatalogDataSource

class CatalogRepository {
    private val source = RemoteCatalogDataSource()

    fun getItems(): List<ResponseCatalogItem>? {
        val res = source.getCatalogItems()
        if (!res.isSuccessful) {
            println(res.message())
            return null
        }

        return res.body()?.items
    }
}