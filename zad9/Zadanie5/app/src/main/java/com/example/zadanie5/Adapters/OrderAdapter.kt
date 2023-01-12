package com.example.zadanie5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Databases.Entities.Order
import com.example.zadanie5.databinding.OrderItemCellBinding

class OrderAdapter(
    private val orders : List<Order>
) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = OrderItemCellBinding.inflate(from,parent,false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bindOrder(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}