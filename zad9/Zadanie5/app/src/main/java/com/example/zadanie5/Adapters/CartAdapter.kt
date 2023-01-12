package com.example.zadanie5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Fragments.CartClickListener
import com.example.zadanie5.Databases.Entities.CartItem
import com.example.zadanie5.databinding.CartItemCellBinding

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val clickListener: CartClickListener
):RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CartItemCellBinding.inflate(from,parent,false)
        return CartViewHolder(parent.context,binding,clickListener)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindCartItem(cartItems[position])
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}