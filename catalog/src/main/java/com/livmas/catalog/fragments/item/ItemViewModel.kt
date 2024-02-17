package com.livmas.catalog.fragments.item

import androidx.lifecycle.ViewModel
import com.livmas.catalog.CatalogManager

class ItemViewModel : ViewModel() {
    val itemData = CatalogManager.openedItem
}