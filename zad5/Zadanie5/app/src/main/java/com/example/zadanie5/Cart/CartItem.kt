package com.example.zadanie5.Cart

import java.util.UUID

class CartItem(
    var name: String,
    var inCart: Int,
    var id: UUID = UUID.randomUUID()
) {
}