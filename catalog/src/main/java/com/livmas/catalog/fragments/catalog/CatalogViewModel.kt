package com.livmas.catalog.fragments.catalog

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.catalog.CatalogManager
import com.livmas.ui.models.PreviewItemModel
import com.livmas.catalog.models.SortingMode
import com.livmas.data.retrofit.repositories.CatalogRepositoryImpl
import com.livmas.ui.createPreviewModelList
import com.livmas.ui.models.enums.ItemTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CatalogViewModel : ViewModel() {
    val catalogContent: LiveData<List<PreviewItemModel>>
        get() = mutableCatalogContent
    private val mutableCatalogContent: MutableLiveData<List<PreviewItemModel>> by lazy {
        MutableLiveData(null)
    }
    val sortingMode: SortingMode
        get() = mutableSortingMode
    private var mutableSortingMode: SortingMode = SortingMode.Rating

    val selectedTag: ItemTag?
        get() = mutableSelectedTag
    private var mutableSelectedTag: ItemTag? = null

    private val catalogRepository = CatalogRepositoryImpl()
    private val catalogManager = CatalogManager()

    fun setSortMode(mode: SortingMode) {
        mutableSortingMode = mode
        mutableCatalogContent.postValue(catalogManager.sortFetchedData(mode))
    }

    fun setTag(tag: ItemTag?) {
        mutableSelectedTag = tag
        mutableCatalogContent.postValue( if (tag != null)
            catalogManager.filterByTag(tag)
        else
            catalogManager.removeTagFilter()
        )
    }

    fun fillAdapterWithData(images: ArrayList<Drawable>) {
        CoroutineScope(Dispatchers.IO).launch {
            catalogManager.setData(fetchData(images))
            selectedTag?.let { catalogManager.filterByTag(it) }
            mutableCatalogContent.postValue(catalogManager.sortFetchedData(mutableSortingMode))
        }
    }

    private fun fetchData(images: ArrayList<Drawable>): List<PreviewItemModel> {
        return catalogRepository.getAllItems()?.run {
            createPreviewModelList(images)
        } ?: arrayListOf()
    }
}