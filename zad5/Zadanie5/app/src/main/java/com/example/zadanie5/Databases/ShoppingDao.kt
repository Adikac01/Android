package com.example.zadanie5.Databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.zadanie5.Databases.Entities.CartItem
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.Databases.Entities.Relations.CategoryWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Transaction
    @Query("SELECT * FROM product_item_table ORDER BY id ASC")
    fun allProductsItems(): Flow<List<ProductItem>>

    @Transaction
    @Query("SELECT * FROM cart_item_table ORDER BY id ASC")
    fun allCartItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductItem(productItem: ProductItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Transaction
    @Query("SELECT * FROM product_item_table WHERE categoryName = :categoryName")
    suspend fun getCategoryWith(categoryName: String): List<CategoryWithProducts>
}