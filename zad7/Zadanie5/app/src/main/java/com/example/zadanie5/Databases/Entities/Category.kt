package com.example.zadanie5.Databases.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Category (
    val categoryID : String ?= null,
    val categoryName: String ?= null
        )