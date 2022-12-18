package com.example.zadanie5.Databases.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    @PrimaryKey(autoGenerate = false)
    val categoryName: String
        )