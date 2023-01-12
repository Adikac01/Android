package com.example.zadanie5.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie5.Databases.Entities.Order

class OrderViewModel: ViewModel() {

    val orders = MutableLiveData<MutableList<Order>>()

    init {
        orders.value = mutableListOf()
    }

    fun addOrder(newOrder: Order){
        orders.value!!.add(newOrder)
    }

}