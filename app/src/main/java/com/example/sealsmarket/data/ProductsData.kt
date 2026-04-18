package com.example.sealsmarket.data

import com.example.sealsmarket.model.Category
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import kotlinx.serialization.Serializable


@Serializable
//Этот класс скорее всего пригодится при считывании json, так как он состоит из двух списков
//Пока он нигде не используется
data class ProductsContent(
    var catList: List<Category>,
    var itemList: List<Item>
)


object ProductsData {

    //Функция должна считывать данные из файла. Этот код нужно переделать
    fun GetCategoriesList(): List<Category>{
        return listOf<Category>(
            Category(
                    id = "cat_jeans",
                    name = "Джинсы"
            ),
            Category(
                     "cat_tshirts",
                     "Футболки"
            ),
            Category(
                    "cat_shirts",
                    "Рубашки"
            ),
            Category(
                    "cat_shoes",
                  "Обувь",
            ),
            Category(
                    "cat_outerwear",
                "Верхняя одежда"
            )
            )
    }

    //Тоже переделать
    fun GetItemsList(): List<Item>{
        return listOf(
            emptyItem, emptyItem, emptyItem, emptyItem, emptyItem, emptyItem, emptyItem
        )
    }
}