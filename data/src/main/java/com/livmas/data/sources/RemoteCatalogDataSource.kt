package com.livmas.data.sources

import com.livmas.data.BASE_URL
import com.livmas.data.models.RemoteCatalogAPI
import com.livmas.data.models.ResponseCatalog
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RemoteCatalogDataSource {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RemoteCatalogAPI::class.java)

    fun getCatalogItems(): Response<ResponseCatalog> =
        api.getCatalog().execute()
}