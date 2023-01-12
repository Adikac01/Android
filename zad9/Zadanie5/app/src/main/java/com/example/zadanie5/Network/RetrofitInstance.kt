package com.example.zadanie5.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().build()
    val api: ShoppingApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://63c01d31e262345656f7ecfa.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ShoppingApi::class.java)
    }
}