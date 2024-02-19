package com.livmas.ui.models

import android.graphics.drawable.Drawable
import com.livmas.ui.models.enums.ItemTag

data class PreviewItemModel (
    val id: String,
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