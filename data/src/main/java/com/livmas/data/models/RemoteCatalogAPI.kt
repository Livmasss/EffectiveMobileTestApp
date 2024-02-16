package com.livmas.data.models

import retrofit2.Call
import retrofit2.http.GET

internal interface RemoteCatalogAPI {
    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010/")
    fun getCatalog(): Call<ResponseCatalog>
}