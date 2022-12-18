package com.example.zadanie5.Databases.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "cart_item_table")
data class CartItem(
    val name: String,
    var inCart: Int,
    var categoryName: String = "",
    @PrimaryKey(autoGenerate = true)var id: Int = 0
) {
}