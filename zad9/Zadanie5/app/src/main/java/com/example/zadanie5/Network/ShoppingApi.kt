package com.example.zadanie5.Network

import com.example.zadanie5.Databases.Entities.Order
import com.example.zadanie5.Databases.Entities.OrderConfirmation
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ShoppingApi {
    @Headers("Content-Type: application/json")
    @POST("order")
    fun makeOrder(@Body orderInfo: Order): Call<OrderConfirmation>
}