package com.example.sealsmarket.model

import androidx.compose.ui.text.font.FontWeight

data class Item(
    val id: String,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val priceInKopecks: Int,
    val imageUrl: String,
    val tags: List<String>,
    val sizes: List<String>,
    val categoryId: String,
    val material: String,
    val weight: String,
    val season: String,
    val countryOfOrigin: String
)

val emptyItem =  Item(
    "item_001",
"Джинсы Slim Fit",
"Классические зауженные джинсы",
"Стильные джинсы зауженного кроя из качественного денима. Идеально подходят для повседневной носки и создания городского образа. Классическая пятикарманная модель с медной фурнитурой. Средняя посадка обеспечивает комфорт в течение всего дня. Деним средней плотности — универсальный выбор для любого сезона.",
 499900,
"https://images.unsplash.com/photo-1542272604-787c3835535d?w=400&h=500&fit=crop",
    listOf(),
    listOf(),
"cat_jeans",
"98% хлопок, 2% эластан",
 "450 г",
"Всесезон",
"Турция"
)

