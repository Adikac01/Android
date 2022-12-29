package com.example.zadanie5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.databinding.CategoryItemCellBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CategoryItemCellBinding.inflate(from,parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    fun updateCategoryList(categoryList: List<Category>){
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(
        private val binding: CategoryItemCellBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bindCategory(category: Category){
            binding.categoryName.text = category.categoryName
        }
    }

}