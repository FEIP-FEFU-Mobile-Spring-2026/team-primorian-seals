package com.example.sealsmarket.data.ProductsData

import Category
import androidx.compose.runtime.toMutableStateList
import cat_new
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.ProductsContent
import com.example.sealsmarket.model.emptyItem

object ExampleProductsContentHandler : IProductsContentReciever
{
    override fun GetProductsContent(): ProductsContent {
        val productsContent = ProductsContent(
            categories = GetExampleCategoriesList().toMutableList(),
            items = GetExampleItemsList().toMutableList()
        );

        return productsContent
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