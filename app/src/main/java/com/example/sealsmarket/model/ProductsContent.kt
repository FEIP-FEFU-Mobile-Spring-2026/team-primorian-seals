package com.example.sealsmarket.model
import Category

data class ProductsContent(
    var categories: List<Category>,
    var items: List<Item>
)