package com.example.zadanie5.Adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Fragments.CartClickListener
import com.example.zadanie5.Databases.Entities.CartItem
import com.example.zadanie5.databinding.CartItemCellBinding

class CartViewHolder(
    private val context: Context,
    private val binding: CartItemCellBinding,
    private val clickListener: CartClickListener
):RecyclerView.ViewHolder(binding.root){

    fun bindCartItem (cartItem: CartItem){
        binding.cartProductName.text = cartItem.name
        binding.itemsInCart.text = cartItem.inCart.toString()
        binding.removeBtn.setOnClickListener{
            clickListener.removeFromCart(cartItem)
        }
    }

}