package com.example.zadanie5.Products

interface ProductClickListener {
    fun displayDescription(productItem: ProductItem)
    fun addItemToCart(productItem: ProductItem)
}