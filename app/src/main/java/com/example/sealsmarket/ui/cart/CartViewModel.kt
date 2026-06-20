package com.example.sealsmarket.ui.cart

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.sealsmarket.model.CartItem
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.Size
import com.example.sealsmarket.model.cat_new
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartViewModel: ViewModel() {
    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state.asStateFlow()

    private lateinit var prefs: SharedPreferences

    fun initPrefs(context: Context) {
        prefs = context.getSharedPreferences(CART_PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun saveCart() {
        val itemsToSave = _state.value.items.map { cartItem ->
            val parts = cartItem.id.split("_", limit = 2)
            val itemId = parts.getOrNull(0) ?: ""
            val sizeName = parts.getOrNull(1) ?: ""
            CartItemData(itemId, sizeName, cartItem.count)
        }
        val json = com.google.gson.Gson().toJson(itemsToSave)
        prefs.edit().putString(CART_ITEMS_KEY, json).apply()
    }

    data class CartItemData(val itemId: String, val sizeName: String, val count: Int)

    companion object {
        private const val CART_PREFS_NAME = "cart_prefs"
        private const val CART_ITEMS_KEY = "cart_items"
    }

    fun restoreCart(catalogItems: List<Item>) {
        val savedJson = prefs.getString(CART_ITEMS_KEY, null) ?: return
        if (catalogItems.isEmpty()) return

        val itemsToRestore = com.google.gson.Gson().fromJson(
            savedJson,
            Array<CartItemData>::class.java
        ).toList()

        val restoredItems = mutableListOf<CartItem>()
        var total = 0
        var cnt = 0

        for (itemData in itemsToRestore) {
            val catalogItem = catalogItems.find { it.id == itemData.itemId } ?: continue
            val size = catalogItem.sizes.find { it.name == itemData.sizeName } ?: continue

            val cartItem = CartItem(
                id = "${itemData.itemId}_${itemData.sizeName}",
                name = catalogItem.name,
                size = size,
                imageUrl = catalogItem.imageUrl,
                priceInKopecks = catalogItem.priceInKopecks,
                count = itemData.count
            )

            restoredItems.add(cartItem)
            total += catalogItem.priceInKopecks * itemData.count
            cnt += itemData.count
        }

        _state.value = CartState(
            items = restoredItems,
            totalPrice = total,
            itemsCnt = cnt
        )
    }

    fun addItem(item: Item, size: Size){
        _state.update { curState ->
            val newList = curState.items.toMutableList()
            var item_id = "${item.id}_${size.name}"
            val index = newList.indexOfFirst { it.id == item_id }
            var newItem: CartItem
            val newPrice = curState.totalPrice + item.priceInKopecks
            val newCnt = curState.itemsCnt + 1

            if (index == -1) {
                newItem = CartItem(item_id, item.name, size, item.imageUrl, item.priceInKopecks, 1)
                newList.add(newItem)
            }
            else {
                var old_cnt = newList[index].count;
                newList[index] = newList[index].copy(count = old_cnt+1);
            }
            curState.copy(items = newList, totalPrice = newPrice, itemsCnt = newCnt);
        }
        saveCart()
    }

    fun addItem(cartItem:CartItem){
        _state.update { curState ->
            val newList = curState.items.toMutableList()
            val index = newList.indexOfFirst { it.id == cartItem.id }
            var newItem: CartItem
            var old_cnt = newList[index].count;

            newList[index] = newList[index].copy(count = old_cnt+1);
            val newPrice = curState.totalPrice + cartItem.priceInKopecks
            val newCnt = curState.itemsCnt + 1
            curState.copy(items = newList, totalPrice = newPrice, itemsCnt = newCnt);
        }
        saveCart()
    }

    fun removeItem(cartItem:CartItem, cnt: Int = 1){
        _state.update { curState ->
            val newList = curState.items.toMutableList()
            val index = newList.indexOfFirst { it.id == cartItem.id }

            if (index == -1) {
                return@update curState
            }

            val oldCnt = newList[index].count

            if (oldCnt - cnt <= 0) {
                newList.removeAt(index)
            }
            else{
                newList[index] = newList[index].copy(count = oldCnt-cnt);
            }

            val newPrice = curState.totalPrice - cartItem.priceInKopecks*cnt
            val newCnt = curState.itemsCnt - cnt
            curState.copy(items = newList, totalPrice = newPrice, itemsCnt = newCnt )
        }
        saveCart()
    }

    fun clearCart(){
        _state.update { curState->
            curState.copy(items=emptyList(), itemsCnt = 0, totalPrice = 0)
        }
        saveCart()
    }

    fun placeOrder() {
        clearCart()
    }
}