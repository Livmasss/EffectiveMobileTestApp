package com.livmas.catalog.fragments.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.catalog.models.ItemModel

internal class ItemViewModel: ViewModel() {
    val mutableModel: MutableLiveData<ItemModel> by lazy {
        MutableLiveData(null)
    }
    val descriptionShown: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }
    val ingredientsShown: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }
}