package com.dbnaza.desafioenjoei.api

import com.dbnaza.desafioenjoei.App
import com.dbnaza.desafioenjoei.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ProductsDataSourceImpl : ProductsDataSource {
    private const val CACHE_SIZE_BYTES: Long = 1024

    private const val CONNECT_TIMEOUT_SECONDS: Long = 10

    private const val BASE_URL = "http://www.mocky.io/"

    private var productsAPI: ProductsAPI

    init {
        val cache = Cache(App.instance.cacheDir, CACHE_SIZE_BYTES)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .cache(cache)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                }
            }
            .build()

        val gson = GsonBuilder()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        productsAPI = retrofit.create<ProductsAPI>(ProductsAPI::class.java)
    }

    override fun getProducts(callback: ResultCallback<List<Product>>) {
        productsAPI.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                try {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                } catch (t: Throwable) {
                    callback.onError(t)
                }
            }

            override fun onFailure(
                call: Call<List<Product>>,
                t: Throwable
            ) {
                callback.onError(t)
            }
        })
    }
}