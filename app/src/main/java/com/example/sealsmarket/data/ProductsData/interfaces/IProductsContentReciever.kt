package com.example.sealsmarket.data.ProductsData.interfaces

import com.example.sealsmarket.model.ProductsContent

interface IProductsContentReciever
{
    suspend fun GetProductsContent() : ProductsContent?;
}