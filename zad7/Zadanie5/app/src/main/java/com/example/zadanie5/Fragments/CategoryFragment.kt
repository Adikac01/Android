package com.example.zadanie5.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie5.Adapters.CategoryAdapter
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.R
import com.example.zadanie5.ViewModels.CartViewModel
import com.example.zadanie5.ViewModels.CategoryViewModel
import com.example.zadanie5.databinding.FragmentCartBinding
import com.example.zadanie5.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        categoryViewModel = ViewModelProvider(activity)[CategoryViewModel::class.java]
        categoryViewModel.fetchItems()
        val categoryAdapter = CategoryAdapter()
        binding.categoryListView.adapter = categoryAdapter
        binding.categoryListView.layoutManager = LinearLayoutManager(requireContext())
        categoryViewModel.categoryList.observe(activity){ categoryList->
            categoryAdapter.updateCategoryList(categoryList)
        }
    }
}