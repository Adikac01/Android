package com.example.zadanie5.Databases.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

data class ProductItem(
    var name: String ?= null,
    var desc: String ?= null,
    var categoryID: String ?= null
) {

}