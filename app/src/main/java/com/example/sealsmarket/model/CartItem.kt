package com.example.sealsmarket.model

//Информация о товаре, добавленном в корзину
data class CartItem(
    val name: String,
    val color: String,
    val size: String,
    val imageUrl: String,
    val priceInRub: Int,
    val count: Int
)

val emptyCartItem = CartItem(
    name = "Футболка современная",
    color = "Чёрный",
    size = "XXL",
    imageUrl = "https://cdn.100sp.ru/pictures/036231728",
    priceInRub = 4998,
    count = 1
)