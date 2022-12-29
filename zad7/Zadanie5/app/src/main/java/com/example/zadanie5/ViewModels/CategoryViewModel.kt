package com.example.zadanie5.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie5.Databases.CategoryRepository
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.Databases.ProductItemRepository

class CategoryViewModel : ViewModel() {

    private val repository = CategoryRepository()
    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> = _categoryList


    fun fetchItems(){
        repository.fetchCategories(_categoryList)
    }

}