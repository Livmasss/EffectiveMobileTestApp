package com.livmas.catalog.models

import android.graphics.drawable.Drawable
import com.livmas.utils.models.ItemTag

data class CatalogItem (
    val images: List<Drawable>,

    val price: Int,
    val oldPrice: Int,
    val unit: Char,
    val discount: Int,

    val title: String,
    val subtitle: String,

    val tags: List<ItemTag>,

    val rating: Float,
    val reviewsCount: Int,

    val isLiked: Boolean
    )