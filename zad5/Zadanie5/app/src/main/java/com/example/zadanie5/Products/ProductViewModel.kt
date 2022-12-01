package com.example.zadanie5.Products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel: ViewModel() {

    var productItems = MutableLiveData<MutableList<ProductItem>?>()

    init{
        productItems.value = mutableListOf()
    }

    fun addProduct(newProduct: ProductItem){
        val list = productItems.value
        list!!.add(newProduct)
        productItems.postValue(list)
    }

    fun displayDescription(productItem: ProductItem){

    }

}