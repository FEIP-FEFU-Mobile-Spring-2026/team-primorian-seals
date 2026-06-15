package com.example.sealsmarket.data.ProductsData

import com.example.sealsmarket.model.Category
import androidx.compose.runtime.toMutableStateList
import com.example.sealsmarket.model.cat_new
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.ProductsContent
import com.example.sealsmarket.model.emptyItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ExampleProductsContentHandler : IProductsContentReciever
{
    override suspend fun GetProductsContent(): ProductsContent {
        return withContext(Dispatchers.IO) {
            val productsContent = ProductsContent(
                categories = GetExampleCategoriesList().toMutableList(),
                items = GetExampleItemsList().toMutableList()
            );

            productsContent
        }
    }

    fun GetExampleCategoriesList(): List<Category>
    {
        return listOf<Category>(cat_new) + listOf<Category>(
            Category(
                id = "New",
                name = "New"
            ),
            Category(
                id = "Popular",
                name = "Popular"
            ),
            Category(
                id = "Sale",
                name = "Sale"
            ),
            Category(
                id = "cat_jeans",
                name = "Джинсы"
            ),
            Category(
                "cat_tshirts",
                "Футболки"
            ),
            Category(
                "cat_shirts",
                "Рубашки"
            ),
            Category(
                "cat_shoes",
                "Обувь",
            ),
            Category(
                "cat_outerwear",
                "Верхняя одежда"
            )
        )
    }

    fun GetExampleItemsList(): List<Item>
    {
        return listOf(
            emptyItem, emptyItem, emptyItem, emptyItem, emptyItem, emptyItem, emptyItem
        )
    }
}