package com.example.sealsmarket.data.api

import com.example.sealsmarket.model.Category
import com.example.sealsmarket.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(
        @Header("Authorization") authorization: String = "Bearer Cmt7wdwFgDIi1_SRX8hlJIExs0jJKPr4axflLpExAxM"
    ): Response<CatalogResponse>
}

data class CatalogResponse(
    val categories: MutableList<Category>,
    val items: MutableList<Item>
)
