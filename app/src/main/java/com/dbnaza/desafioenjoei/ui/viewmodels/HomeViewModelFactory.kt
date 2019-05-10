package com.dbnaza.desafioenjoei.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbnaza.desafioenjoei.api.ProductsDataSource

class HomeViewModelFactory(
        private val productsDataSource: ProductsDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(productsDataSource) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}