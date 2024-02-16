package com.livmas.catalog.models

import android.graphics.drawable.Drawable

data class CatalogItem (
    val images: List<Drawable>,

    // Price with currency symbol
    val price: String,
    val oldPrice: String,

    val title: String,
    val subtitle: String,

    val discount: Int,

    val rating: Float,
    val reviewsCount: Int,

    val isLiked: Boolean
    )