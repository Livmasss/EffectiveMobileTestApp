package com.livmas.catalog.models

import android.graphics.drawable.Drawable
import com.livmas.data.models.ItemCharacteristics
import com.livmas.data.models.ResponseCatalogItem

internal data class ItemModel (
    val images: List<Drawable>,

    val title: String,
    val subtitle: String,

    val price: Int,
    val oldPrice: Int,
    val unit: Char,
    val discount: Int,

    val rating: Float,
    val reviewsCount: Int,

    val available: Int,
    val description: String,
    val info: List<ItemCharacteristics>,
    val ingredients: String
) {
    constructor(response: ResponseCatalogItem, images: List<Drawable>) : this(
        images, response.title, response.subtitle,
        response.price.priceWithDiscount.toInt(), response.price.price.toInt(),
        response.price.unit[0], response.price.discount,
        response.feedback.rating, response.feedback.count,
        response.available, response.description,
        response.info, response.ingredients
    )
}