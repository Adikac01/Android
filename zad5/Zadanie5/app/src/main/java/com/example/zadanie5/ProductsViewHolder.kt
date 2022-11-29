package com.example.zadanie5

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
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
    }

}