package com.example.zadanie5.Adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Databases.Entities.Order
import com.example.zadanie5.databinding.OrderItemCellBinding

class OrderViewHolder(
    private val binding: OrderItemCellBinding
):RecyclerView.ViewHolder(binding.root) {

    fun bindOrder(order: Order) {
        val text = order.firstName + " " + order.lastName
        binding.person.text = text
        binding.email.text = order.email
        val cost = order.cost.toString() + " zł"
        binding.combinedCost.text = cost
    }
}