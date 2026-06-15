package com.example.sealsmarket.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.Category
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.ProductsContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val productsContentHandler: IProductsContentReciever
) : ViewModel() {

    // 0 = загрузка, 1 = успех, 2 = ошибка
    private val _loadingState = MutableStateFlow<Int>(0)
    val loadingState: StateFlow<Int> = _loadingState.asStateFlow()

    private val _productsContent = MutableStateFlow<ProductsContent?>(null)
    val productsContent: StateFlow<ProductsContent?> = _productsContent.asStateFlow()

    private val _selectedCatId = MutableStateFlow<String>("")
    val selectedCatId: StateFlow<String> = _selectedCatId.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _loadingState.value = 0 // загрузка
            val content = productsContentHandler.GetProductsContent()
            if (content != null && content.categories.isNotEmpty()) {
                _productsContent.value = content
                _selectedCatId.value = content.categories[0].id
                _loadingState.value = 1 // успех
            } else {
                _loadingState.value = 2 // ошибка
            }
        }
    }

    fun selectCategory(id: String) {
        _selectedCatId.value = id
    }

    fun getFilteredItems(): List<Item> {
        val content = _productsContent.value
        val currentCatId = _selectedCatId.value
        return content?.items?.filter { item ->
            item.categoryId == currentCatId || item.tags.contains(currentCatId)
        } ?: emptyList()
    }
    
    fun getCategories(): List<Category> {
        return _productsContent.value?.categories ?: emptyList()
    }
}