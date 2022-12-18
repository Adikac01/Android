package com.example.zadanie5.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie5.Databases.Entities.ProductItem

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