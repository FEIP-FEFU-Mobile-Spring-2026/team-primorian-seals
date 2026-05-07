package com.example.sealsmarket.model
import Category

data class ProductsContent(
    var categories: MutableList<Category>,
    var items: MutableList<Item>
)