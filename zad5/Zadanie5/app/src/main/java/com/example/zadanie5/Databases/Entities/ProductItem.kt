package com.example.zadanie5.Databases.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "product_item_table")
data class ProductItem(
    var name: String,
    var desc: String,
    var categoryName: String = "",
    @PrimaryKey(autoGenerate = true)var id: Int = 0
) {
}