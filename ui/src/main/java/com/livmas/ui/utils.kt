package com.livmas.ui

import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.livmas.data.retrofit.models.ResponseCatalogItem
import com.livmas.domain.models.LikedItemModel
import com.livmas.ui.models.PreviewItemModel
import com.livmas.ui.models.enums.ItemTag

fun PreviewItemModel.createLikedModel(): LikedItemModel = run {
    LikedItemModel(id, images, price, oldPrice, unit, discount, title, subtitle, rating, reviewsCount, isLiked)
}

fun LikedItemModel.createPreviewModel(tags: List<ItemTag>): PreviewItemModel = run {
    PreviewItemModel(id, images, price, oldPrice, unit, discount, title, subtitle, tags, rating, reviewsCount, isLiked)
}

fun getItemsImages(resources: Resources, theme: Theme) = ArrayList<Drawable>().apply {
    addDrawable(getDrawableById(resources, R.drawable.catalog_image_0, theme))
    addDrawable(getDrawableById(resources, R.drawable.catalog_image_1, theme))
    addDrawable(getDrawableById(resources, R.drawable.catalog_image_2, theme))
    addDrawable(getDrawableById(resources, R.drawable.catalog_image_3, theme))
    addDrawable(getDrawableById(resources, R.drawable.catalog_image_4, theme))
    addDrawable(getDrawableById(resources, R.drawable.catalog_image_5, theme))
}


private fun getDrawableById(resources: Resources, resId: Int, theme: Theme) =
    ResourcesCompat.getDrawable(resources, resId, theme)
private fun ArrayList<Drawable>.addDrawable(drawable: Drawable?) = drawable?.let { add(it) }

fun List<ResponseCatalogItem>.createPreviewModelList(images: List<Drawable>) = map {
    PreviewItemModel(
        it.id,
        chooseImages(it.id, images),
        it.price.priceWithDiscount.toInt(),
        it.price.price.toInt(),
        it.price.unit[0],
        it.price.discount,
        it.title,
        it.subtitle,
        it.tags.mapNotNull { tag ->
            getItemTagByString(tag)
        },
        it.feedback.rating,
        it.feedback.count,
        false
    )
}


private fun chooseImages(id: String, images: List<Drawable>): List<Drawable> {
    val list = ArrayList<Drawable>(2)

    fun item0() {
        list.add(images[5])
        list.add(images[4])
    }

    fun item1() {
        list.add(images[0])
        list.add(images[1])
    }

    fun item2() {
        list.add(images[4])
        list.add(images[5])
    }

    fun item3() {
        list.add(images[2])
        list.add(images[3])
    }

    fun item4() {
        list.add(images[1])
        list.add(images[2])
    }

    fun item5() {
        list.add(images[5])
        list.add(images[0])
    }

    fun item6() {
        list.add(images[3])
        list.add(images[2])
    }

    fun item7() {
        list.add(images[0])
        list.add(images[4])
    }

    when (id) {
        "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> item0()
        "54a876a5-2205-48ba-9498-cfecff4baa6e" -> item1()
        "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> item2()
        "16f88865-ae74-4b7c-9d85-b68334bb97db" -> item3()
        "26f88856-ae74-4b7c-9d85-b68334bb97db" -> item4()
        "15f88865-ae74-4b7c-9d81-b78334bb97db" -> item5()
        "88f88865-ae74-4b7c-9d81-b78334bb97db" -> item6()
        "55f58865-ae74-4b7c-9d81-b78334bb97db" -> item7()
    }

    return list
}

private fun getItemTagByString(string: String) =
    when (string) {
        "face" -> ItemTag.Face
        "body" -> ItemTag.Body
        "suntan" -> ItemTag.Suntan
        "mask" -> ItemTag.Mask
        else -> null
    }