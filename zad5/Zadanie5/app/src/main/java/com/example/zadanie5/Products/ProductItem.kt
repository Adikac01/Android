package com.example.zadanie5.Products

import java.util.UUID

class ProductItem(
    var name: String,
    var desc: String,
    var id: UUID = UUID.randomUUID()
) {
}