package com.example.sealsmarket.model

data class Item(
    val id: String,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val priceInKopecks: Int,       // В JSON число, в Kotlin Int/Long
    val imageUrl: String,
    val tags: List<String>,        // В JSON массив строк
    val sizes: List<Size>,         // В JSON массив объектов Size
    val categoryId: String,
    val material: String,
    val weight: String,
    val season: String,
    val countryOfOrigin: String
)

data class Size(
    val id: String,
    val name: String
)

val emptyItem =  Item(
    "item_001",
"Футболка современная",
"Классическая стильная футболка кошка чёрный",
"Идеально подходит для повседневной носки и создания современного и необыкновенного образа. С ярким принтом вы определённо станете центром внимания. Подойдёт в качестве одежды для официальной встречи или мероприятия. ",
 499900,
"https://avatars.mds.yandex.net/i?id=84c32eb059489c317f944b5579d9fa3acbe96139-5249054-images-thumbs&ref=rim&n=33&w=241&h=275",
    listOf(),
    listOf(),
"cat_tshirts",
"98% хлопок, 2% эластан",
 "250 г",
"Всесезон",
"Турция"
)


