package com.livmas.profile.fragments.liked

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.data.retrofit.repositories.CatalogRepositoryImpl
import com.livmas.domain.usecases.liked.GetLikedItemsUseCase
import com.livmas.ui.createPreviewModel
import com.livmas.ui.models.PreviewItemModel
import com.livmas.ui.updateRequiredImages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class LikedViewModel : ViewModel() {

    private val getLikedItemsUseCase = GetLikedItemsUseCase()
    val catalogRepo: CatalogRepositoryImpl by inject(CatalogRepositoryImpl::class.java)

    val likedList: LiveData<List<PreviewItemModel>>
        get() = mutableLikedList
    private val mutableLikedList: MutableLiveData<List<PreviewItemModel>> by lazy {
        MutableLiveData()
    }


    fun fillAdapterWithData(images: ArrayList<Drawable>) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = getLikedItemsUseCase.execute()?.map {
                val previews = it.createPreviewModel(listOf())
                previews.updateRequiredImages(images)
                previews
            }
            mutableLikedList.postValue(list)
        }
    }
}