package com.example.sealsmarket.model

//Информация о товаре, добавленном в корзину
data class CartItem(
    val id: String,
    val name: String,
    val size: Size,
    val imageUrl: String,
    val priceInKopecks: Int,
    val count: Int,
    val color: String = "Цвет", // выбор цвета пока не предусмотрен в тз
)


val emptyCartItem = CartItem(
    id = "0",
    name = "Футболка современная",
    color = "Чёрный",
    size = Size(id="001", name = "XXL"),
    imageUrl = "https://cdn.100sp.ru/pictures/036231728",
    priceInKopecks = 4998,
    count = 1
)