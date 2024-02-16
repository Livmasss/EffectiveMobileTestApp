package com.livmas.catalog.fragments.catalog

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.catalog.models.CatalogItem
import com.livmas.catalog.models.SortingMode
import com.livmas.data.repositories.CatalogRepository
import com.livmas.utils.models.ItemTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {
    val catalogContent: LiveData<List<CatalogItem>>
        get() = mutableCatalogContent
    private val mutableCatalogContent: MutableLiveData<List<CatalogItem>> by lazy {
        MutableLiveData(null)
    }
    val sortingMode: SortingMode
        get() = mutableSortingMode
    private var mutableSortingMode: SortingMode = SortingMode.Rating

    private val catalogRepository = CatalogRepository()
    private var fetchedData: List<CatalogItem>? = null

    fun setSortMode(mode: SortingMode) {
        mutableSortingMode = mode

        mutableCatalogContent.postValue(sortedFetchedData())
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

    fun fillAdapterWithData(images: ArrayList<Drawable>) {
        CoroutineScope(Dispatchers.IO).launch {
            fetchedData = fetchData(images)

            mutableCatalogContent.postValue(sortedFetchedData())
        }
    }

    //Returns sorted list from fetchedData variable, based on sortingMode
    private fun sortedFetchedData(): List<CatalogItem>? {
        return fetchedData?.run {
            when (sortingMode) {
                SortingMode.Rating -> sortedByDescending { it.rating }
                SortingMode.Descend -> sortedByDescending { it.price }
                SortingMode.Increase -> sortedBy { it.price }
            }
        }
    }

    private fun fetchData(images: ArrayList<Drawable>): List<CatalogItem> {
        return catalogRepository.getItems()?.map {
            CatalogItem(
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
                it.feedback?.rating ?: 0f,
                it.feedback?.count ?: 0,
                false
            )
        } ?: arrayListOf()
    }

    private fun getItemTagByString(string: String) =
        when (string) {
            "face" -> ItemTag.Face
            "body" -> ItemTag.Body
            "suntan" -> ItemTag.Suntan
            "mask" -> ItemTag.Mask
            else -> null
        }
}