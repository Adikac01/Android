package com.example.zadanie5.Databases

import android.app.Application

class ProductApplication: Application() {

    private val database by lazy { ProductItemDatabase.getDatabase(this) }
    val repository by lazy { ProductItemRepository(database.productItemDao()) }

}