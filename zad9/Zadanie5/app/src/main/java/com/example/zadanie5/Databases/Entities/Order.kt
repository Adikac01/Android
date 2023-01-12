package com.example.zadanie5.Databases.Entities

import android.provider.ContactsContract.CommonDataKinds.Email
import java.util.UUID

data class Order(
    var firstName: String = "",
    var lastName: String = "",
    var email: String  = "",
    var cost: Double,
    var id: UUID = UUID.randomUUID()
)

data class OrderConfirmation(
    val id: Int,
    val confirmed: Boolean
) : java.io.Serializable
