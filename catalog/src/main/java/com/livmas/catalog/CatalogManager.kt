package com.livmas.catalog

import com.livmas.catalog.models.CatalogItem
import com.livmas.catalog.models.SortingMode
import com.livmas.utils.models.ItemTag

internal class CatalogManager {
    companion object {
        var openedItem: CatalogItem? = null
    }

    private var initialData: List<CatalogItem>? = null
    private var filteredData: List<CatalogItem>? = initialData

    fun setData(data: List<CatalogItem>) {
        initialData = data
        filteredData = data.toList()
    }

    //Returns sorted list from fetchedData variable, based on sortingMode
    fun sortFetchedData(mode: SortingMode): List<CatalogItem>? {
        return filteredData?.run {
            when (mode) {
                SortingMode.Rating -> sortedByDescending { it.rating }
                SortingMode.Descend -> sortedByDescending { it.price }
                SortingMode.Increase -> sortedBy { it.price }
            }
        }
    }

    fun filterByTag(tag: ItemTag): List<CatalogItem>? {
        return initialData?.mapNotNull {
            if (tag in it.tags) {
                it
            }
            else
                null
        }.let {
            filteredData = it
            it
        }
    }

    fun removeTagFilter(): List<CatalogItem>? {
        filteredData = initialData
        return filteredData
    }
}