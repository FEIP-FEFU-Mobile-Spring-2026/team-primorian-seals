package com.example.sealsmarket.data.ProductsData.interfaces

import com.example.sealsmarket.model.ProductsContent

interface IProductsContentReciever
{
    fun GetProductsContent() : ProductsContent?;
}