package com.livmas.data.models

data class ResponseCatalogItem (
    val id: String,
    val title: String,
    val subtitle: String,
    val price: ItemPrice,
    val feedback: ItemFeedback,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<ItemCharacteristics>,
    val ingredients: String
    ) {

    data class ItemPrice(
        val price: String,
        val discount: Int,
        val priceWithDiscount: String,
        val unit: String
        )

    data class ItemFeedback (
        val count: Int,
        val rating: Float
        )
}
