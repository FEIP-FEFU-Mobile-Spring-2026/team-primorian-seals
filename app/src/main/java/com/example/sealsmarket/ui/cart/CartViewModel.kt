package com.example.sealsmarket.ui.cart

import androidx.lifecycle.ViewModel
import com.example.sealsmarket.model.CartItem
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartViewModel: ViewModel() {
    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state.asStateFlow()

    fun addItem(item: Item, size: Size){
        _state.update { curState ->
            val newList = curState.items.toMutableList()
            var item_id = "${item.id}_${size.name}"
            val index = newList.indexOfFirst { it.id == item_id }
            var newItem: CartItem

            if (index == -1) {
                newItem = CartItem(item_id, item.name, size, item.imageUrl, item.priceInKopecks, 1)
                newList.add(newItem)
            }
            else {
                var old_cnt = newList[index].count;
                newList[index] = newList[index].copy(count = old_cnt+1);
            }
            curState.copy(items = newList);
        }
    }

    fun addItem(cartItem:CartItem){
        _state.update { curState ->
            val newList = curState.items.toMutableList()
            val index = newList.indexOfFirst { it.id == cartItem.id }
            var newItem: CartItem
            var old_cnt = newList[index].count;

            newList[index] = newList[index].copy(count = old_cnt+1);
            curState.copy(items = newList);
        }
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
            curState.copy(items = newList)
        }
    }

    fun clearCart(){
        _state.update { curState->
            curState.copy(items=emptyList())
        }
    }
}