package com.example.sealsmarket.data.ProductsData

import com.example.sealsmarket.model.Category
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.data.api.ApiClient
import com.example.sealsmarket.model.ProductsContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiProductsContentHandler : IProductsContentReciever {

    override suspend fun GetProductsContent(): ProductsContent? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.catalogApi.getCatalog()
                
                if (response.isSuccessful && response.body() != null) {
                    val catalogResponse = response.body()!!
                    val content = ProductsContent(
                        categories = catalogResponse.categories,
                        items = catalogResponse.items
                    )
                    
                    // Добавляем категории для тегов
                    addVirtualCategories(content)
                    content
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun addVirtualCategories(content: ProductsContent) {
        if (content.items.any { it.tags.contains("New") }) {
            content.categories.add(0, Category(id = "New", name = "Новинки"))
        }
        if (content.items.any { it.tags.contains("Popular") }) {
            content.categories.add(0, Category(id = "Popular", name = "Популярное"))
        }
        if (content.items.any { it.tags.contains("Sale") }) {
            content.categories.add(0, Category(id = "Sale", name = "Распродажа"))
        }
    }
}
