package com.dbnaza.desafioenjoei.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dbnaza.desafioenjoei.ViewFlipperStates
import com.dbnaza.desafioenjoei.api.Product
import com.dbnaza.desafioenjoei.api.ProductsDataSource
import com.dbnaza.desafioenjoei.api.ResultCallback

class HomeViewModel(private val productsDataSource: ProductsDataSource) : ViewModel() {
    val flipperChildLiveData = MutableLiveData<@ViewFlipperStates.States Int>()
    val resultLiveData = MutableLiveData<List<Product>>()
    val headerVisibilityLiveData = MutableLiveData<Int>()

    fun getProducts() {
        headerVisibilityLiveData.value = View.VISIBLE

        flipperChildLiveData.value = ViewFlipperStates.STATE_LOADING

        productsDataSource.getProducts(object : ResultCallback<List<Product>> {
            override fun onSuccess(result: List<Product>) {
                flipperChildLiveData.value = ViewFlipperStates.STATE_SUCCESS
                resultLiveData.value = result
            }

            override fun onError(t: Throwable) {
                flipperChildLiveData.value = ViewFlipperStates.STATE_ERROR
                headerVisibilityLiveData.value = View.GONE
            }
        })
    }
}