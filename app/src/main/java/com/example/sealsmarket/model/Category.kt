package com.example.sealsmarket.model
import android.content.Context
import kotlinx.serialization.*
import kotlinx.serialization.json.*
@Serializable
data class Category(
    val id: String,
    val name: String
)
val cat_new = Category(id = "cat_new", name = "Новинки")