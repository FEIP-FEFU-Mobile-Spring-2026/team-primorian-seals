package com.example.sealsmarket.ui.cart

import com.example.sealsmarket.model.CartItem

data class CartState(
    val items: List<CartItem> = emptyList(),
    val totalPrice: Int = 0,
    val itemsCnt: Int = 0
)