package com.example.sealsmarket.data.ProductsData

import AssetsJsonReader
import Category
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.ProductsContent

class AssetsProductsContentHandler(val assetsJsonReader: AssetsJsonReader) : IProductsContentReciever
{
    override fun GetProductsContent(): ProductsContent?
    {
        val handler = assetsJsonReader.ReadFromAssets<ProductsContent>("resources/products.json");

        // Добавляем категорию вручную
        handler?.categories?.add(0, Category(id = "New", name = "Новинки"))
        handler?.categories?.add(1, Category(id = "Popular", name = "Популярное"))
        handler?.categories?.add(2, Category(id = "Sale", name = "Распродажа"))

        return handler;
    }
}