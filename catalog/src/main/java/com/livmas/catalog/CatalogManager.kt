package com.livmas.catalog

import com.livmas.ui.models.PreviewItemModel
import com.livmas.catalog.models.SortingMode
import com.livmas.ui.models.enums.ItemTag

internal class CatalogManager {
    private var initialData: List<PreviewItemModel>? = null
    private var filteredData: List<PreviewItemModel>? = initialData

    fun setData(data: List<PreviewItemModel>) {
        initialData = data
        filteredData = data.toList()
    }

    //Returns sorted list from fetchedData variable, based on sortingMode
    fun sortFetchedData(mode: SortingMode): List<PreviewItemModel>? {
        return filteredData?.run {
            when (mode) {
                SortingMode.Rating -> sortedByDescending { it.rating }
                SortingMode.Descend -> sortedByDescending { it.price }
                SortingMode.Increase -> sortedBy { it.price }
            }
        }
    }

    fun filterByTag(tag: ItemTag): List<PreviewItemModel>? {
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

    fun removeTagFilter(): List<PreviewItemModel>? {
        filteredData = initialData
        return filteredData
    }
}