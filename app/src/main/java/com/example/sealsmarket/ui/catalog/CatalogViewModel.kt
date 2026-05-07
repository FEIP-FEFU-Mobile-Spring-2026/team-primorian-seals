package com.example.sealsmarket.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.ProductsContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val productsContentHandler: IProductsContentReciever
) : ViewModel() {

    private val _productsContent = MutableStateFlow<ProductsContent?>(null)
    val productsContent: StateFlow<ProductsContent?> = _productsContent.asStateFlow()

    private val _selectedCatId = MutableStateFlow<String>("")
    val selectedCatId: StateFlow<String> = _selectedCatId.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val content = productsContentHandler.GetProductsContent()
            _productsContent.value = content
            if (content?.categories?.isNotEmpty() == true) {
                _selectedCatId.value = content.categories[0].id
            }
        }
    }

    fun selectCategory(id: String) {
        _selectedCatId.value = id
    }

    fun getFilteredItems(): List<Item> {
        val currentCatId = _selectedCatId.value
        return _productsContent.value?.items?.filter { item ->
            item.categoryId == currentCatId || item.tags.contains(currentCatId)
        } ?: emptyList()
    }
}