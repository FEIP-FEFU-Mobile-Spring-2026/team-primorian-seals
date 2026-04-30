package com.example.sealsmarket.data.ProductsData

import AssetsJsonReader
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.ProductsContent

class AssetsProductsContentHandler(val assetsJsonReader: AssetsJsonReader) : IProductsContentReciever
{
    override fun GetProductsContent(): ProductsContent?
    {
        return assetsJsonReader.ReadFromAssets<ProductsContent>("resources/products.json")
    }
}