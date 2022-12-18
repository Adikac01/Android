package com.example.zadanie5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Fragments.ProductClickListener
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.databinding.ProductItemCellBinding

class ProductsAdapter(
    private val productItems: List<ProductItem>,
    private val clickListener: ProductClickListener
):RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductItemCellBinding.inflate(from,parent,false)
        return ProductsViewHolder(parent.context, binding,clickListener)
    }

    override fun getItemCount(): Int {
        return productItems.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindProductItem(productItems[position])
    }
    fun addToCart(holder: ProductsViewHolder, position: Int){
        holder.bindProductItem(productItems[position])
    }
}