package com.dbnaza.desafioenjoei.api

interface ProductsDataSource {
    fun getProducts(callback: ResultCallback<List<Product>>)
}