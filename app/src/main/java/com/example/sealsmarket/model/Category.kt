package com.example.sealsmarket.model

data class Category(
    val id: String,       // Должно совпадать с "id" в JSON
    val name: String      // Должно совпадать с "name" в JSON
)
val cat_new = Category(id = "cat_new", name = "Новинки")