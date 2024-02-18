package com.livmas.catalog.fragments.item

import android.graphics.drawable.Drawable
import com.livmas.catalog.models.ItemModel

internal class ItemKeeper {
    companion object {
        var openedItem: ItemModel? = null
        var openedItemId: String? = null
        var openedItemImages: List<Drawable>? = null
    }
}