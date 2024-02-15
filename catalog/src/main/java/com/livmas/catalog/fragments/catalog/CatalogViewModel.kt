package com.livmas.catalog.fragments.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.catalog.models.CatalogItem

class CatalogViewModel : ViewModel() {
    val catalogContent: LiveData<ArrayList<CatalogItem>>
        get() = mutableCatalogContent
    private val mutableCatalogContent: MutableLiveData<ArrayList<CatalogItem>> by lazy {
        MutableLiveData(ArrayList(0))
    }

    init {
        mutableCatalogContent.value?.add(
            CatalogItem(
                ArrayList(), "1", "1", "2", "2", 0, 4.5f, 10, false
            )
        )
        mutableCatalogContent.value?.add(
            CatalogItem(
                ArrayList(), "2", "2", "3", "3", 0, 4.5f, 10, false
            )
        )
        mutableCatalogContent.value?.add(
            CatalogItem(
                ArrayList(), "3", "3", "4", "4", 20, 4.5f, 10, false
            )
        )
        mutableCatalogContent.value?.add(
            CatalogItem(
                ArrayList(), "4", "4", "5", "5", 50, 4.5f, 10, false
            )
        )
    }
}