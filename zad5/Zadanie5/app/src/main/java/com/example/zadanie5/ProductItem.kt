package com.example.zadanie5

import java.util.UUID

class ProductItem(
    var name: String,
    var desc: String,
    var id: UUID = UUID.randomUUID()
) {
}