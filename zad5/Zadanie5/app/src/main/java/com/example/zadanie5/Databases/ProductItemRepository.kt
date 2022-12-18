package com.example.zadanie5.Databases

import androidx.annotation.WorkerThread
import com.example.zadanie5.Databases.Entities.ProductItem
import kotlinx.coroutines.flow.Flow

class ProductItemRepository(private val productItemDao: ShoppingDao) {

    val allProductItems: Flow<List<ProductItem>> = productItemDao.allProductsItems()

    @WorkerThread
    suspend fun insertProductItem(productItem: ProductItem){
        productItemDao.insertProductItem(productItem)
    }



}