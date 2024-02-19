package com.livmas.domain.models

import android.graphics.drawable.Drawable

data class LikedItemModel (
    val id: String,
    val images: List<Drawable>,

    val price: Int,
    val oldPrice: Int,
    val unit: Char,
    val discount: Int,

    val title: String,
    val subtitle: String,

    val rating: Float,
    val reviewsCount: Int,

    val isLiked: Boolean
    )