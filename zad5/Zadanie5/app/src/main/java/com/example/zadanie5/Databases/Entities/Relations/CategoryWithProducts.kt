package com.example.zadanie5.Databases.Entities.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.Databases.Entities.ProductItem

data class CategoryWithProducts (
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "categoryName"
    )
    val products: List<ProductItem>
        )