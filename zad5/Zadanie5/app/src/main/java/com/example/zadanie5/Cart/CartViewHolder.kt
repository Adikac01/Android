package com.example.zadanie5.Cart

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
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