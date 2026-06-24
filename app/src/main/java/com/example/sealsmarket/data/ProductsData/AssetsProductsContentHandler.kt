package com.example.sealsmarket.data.ProductsData

import com.example.sealsmarket.model.Category
import AssetsJsonReader
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.ProductsContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetsProductsContentHandler(val assetsJsonReader: AssetsJsonReader) : IProductsContentReciever
{
    override suspend fun GetProductsContent(): ProductsContent?
    {
        return withContext(Dispatchers.IO) {
            val handler = assetsJsonReader.ReadFromAssets<ProductsContent>("resources/products.json");

            // Добавляем категорию вручную
            handler?.categories?.add(0, Category(id = "New", name = "Новинки"))
            handler?.categories?.add(1, Category(id = "Popular", name = "Популярное"))
            handler?.categories?.add(2, Category(id = "Sale", name = "Распродажа"))

            handler
        }
    }
}