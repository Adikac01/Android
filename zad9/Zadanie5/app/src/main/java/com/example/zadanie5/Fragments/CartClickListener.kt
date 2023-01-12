package com.example.zadanie5.Fragments

import com.example.zadanie5.Databases.Entities.CartItem

interface CartClickListener {
    fun removeFromCart(cartItem: CartItem)
}