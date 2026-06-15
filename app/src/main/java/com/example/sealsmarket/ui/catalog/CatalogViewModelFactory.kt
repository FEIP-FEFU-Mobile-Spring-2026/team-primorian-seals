package com.example.sealsmarket.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever

class CatalogViewModelFactory(
    private val handler: IProductsContentReciever
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatalogViewModel(handler) as T
    }
}
