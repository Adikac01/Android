package com.example.zadanie5.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.Databases.ProductItemRepository

class ProductViewModel: ViewModel() {


    private val repository = ProductItemRepository()
    private val _productItems = MutableLiveData<List<ProductItem>>()
    val productItems:  LiveData<List<ProductItem>> = _productItems

    fun fetchItems(){
        repository.fetchItems(_productItems)
    }

}