package com.dbnaza.desafioenjoei.api

import retrofit2.Call
import retrofit2.http.GET

interface ProductsAPI {
    @GET("v2/5cd48f393500006fa77a5657")
    fun getProducts(): Call<List<Product>>
}