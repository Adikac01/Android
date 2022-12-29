package com.example.zadanie5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Fragments.ProductClickListener
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.databinding.ProductItemCellBinding

class ProductsAdapter(
    private val clickListener: ProductClickListener
):RecyclerView.Adapter<ProductsViewHolder>() {

    private val productList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductItemCellBinding.inflate(from,parent,false)
        return ProductsViewHolder(parent.context, binding,clickListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindProductItem(productList[position])
    }
    fun updateProductList(productList: List<ProductItem>){
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }
}