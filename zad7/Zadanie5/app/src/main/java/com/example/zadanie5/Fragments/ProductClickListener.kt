package com.example.zadanie5.Fragments

import com.example.zadanie5.Databases.Entities.ProductItem

interface ProductClickListener {
    fun displayDescription(productItem: ProductItem)
    fun addItemToCart(productItem: ProductItem)
}