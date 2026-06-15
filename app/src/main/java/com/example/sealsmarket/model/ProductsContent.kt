package com.example.sealsmarket.model

import com.example.sealsmarket.model.Category

data class ProductsContent(
    var categories: MutableList<Category>,
    var items: MutableList<Item>
)