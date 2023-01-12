package com.example.zadanie5.Adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Fragments.ProductClickListener
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.databinding.ProductItemCellBinding

class ProductsViewHolder(
    private val context: Context,
    private val binding: ProductItemCellBinding,
    private val clickListener: ProductClickListener
):RecyclerView.ViewHolder(binding.root) {

    fun bindProductItem (productItem: ProductItem){
        binding.productName.text = productItem.name
        binding.productCellContainer.setOnClickListener {
            clickListener.displayDescription(productItem)
        }
        binding.addBtn.setOnClickListener{
            clickListener.addItemToCart(productItem)
        }
        val text = productItem.price.toString() + " zł"
        binding.price.text = text
    }

}