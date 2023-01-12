package com.example.zadanie5.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie5.Adapters.ProductsAdapter
import com.example.zadanie5.DescriptionActivity
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.ViewModels.ProductViewModel
import com.example.zadanie5.databinding.FragmentProductBinding


class ProductFragment : Fragment(), ProductClickListener {

    private lateinit var binding: FragmentProductBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        productViewModel = ViewModelProvider(activity)[ProductViewModel::class.java]
        productViewModel.fetchItems()
        val productsAdapter = ProductsAdapter(this)
        binding.productsRecyclerView.adapter = productsAdapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productViewModel.productItems.observe(activity){ productItems->
            productsAdapter.updateProductList(productItems)
        }
    }

    override fun displayDescription(productItem: ProductItem) {
        val desc = productItem.desc
        Intent(activity, DescriptionActivity::class.java).also{
            it.putExtra("EXTRA_DESC", desc)
            startActivity(it)
        }
    }

    override fun addItemToCart(productItem: ProductItem) {
        ProductsToCartFragment(productItem).show(parentFragmentManager, "addItemsTag")
    }
}