package com.example.zadanie5.Databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zadanie5.Databases.Entities.CartItem
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.Databases.Entities.ProductItem

@Database(entities = [ProductItem::class,CartItem::class,Category::class], version = 1, exportSchema = false)
abstract class ProductItemDatabase: RoomDatabase() {
    abstract fun ShoppingDao(): ShoppingDao

    companion object{
        @Volatile
        private var INSTANCE: ProductItemDatabase? = null

        fun getDatabase(context: Context):ProductItemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductItemDatabase::class.java,
                    "product_item_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}