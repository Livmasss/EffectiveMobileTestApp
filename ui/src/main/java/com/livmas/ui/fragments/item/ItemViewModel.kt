package com.livmas.ui.fragments.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.ui.models.ItemModel

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