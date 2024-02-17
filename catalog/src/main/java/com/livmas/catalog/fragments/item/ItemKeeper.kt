package com.livmas.catalog.fragments.item

import com.livmas.catalog.models.ItemModel

internal class ItemKeeper {
    companion object {
        var openedItem: ItemModel? = null
    }
}