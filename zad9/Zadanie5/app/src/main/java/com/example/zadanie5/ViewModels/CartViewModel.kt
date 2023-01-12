package com.example.zadanie5.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie5.Databases.Entities.CartItem
import com.example.zadanie5.Databases.Entities.ProductItem
import java.math.RoundingMode

class CartViewModel: ViewModel() {

    var cartItems = MutableLiveData<MutableList<CartItem>?>()

    init{
        cartItems.value = mutableListOf()
    }

    fun addToCart(newItem: ProductItem, number: Int){
        val cartItem = CartItem(newItem.name, number, newItem.price!!)
        val item = cartItems.value!!.firstOrNull{it.name == cartItem.name}
        if(item == null){
            cartItems.value!!.add(cartItem)
        }else{
            item.inCart += number
        }
    }

    fun removeFromCart(cartItem: CartItem){
        val item = cartItems.value!!.find{it.id == cartItem.id}
        cartItems.value = cartItems.value!!.toMutableList().apply{
            remove(item)
        }. toMutableList()
    }

    fun getCombinedPrice() : Double {
        var price = 0.0
        for (item in cartItems.value!!){
            price += item.inCart * item.price
        }
        val roundedUp = price.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        return roundedUp
    }

    fun clearCart(){
        cartItems.value!!.clear()
    }
}