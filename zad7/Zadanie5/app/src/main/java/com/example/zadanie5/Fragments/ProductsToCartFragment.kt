package com.example.zadanie5.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie5.Databases.Entities.ProductItem
import com.example.zadanie5.ViewModels.CartViewModel
import com.example.zadanie5.ViewModels.ProductViewModel
import com.example.zadanie5.databinding.FragmentProductsToCartBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductsToCartFragment(var productItem: ProductItem) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductsToCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductsToCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        cartViewModel = ViewModelProvider(activity).get(CartViewModel::class.java)
        productViewModel = ViewModelProvider(activity).get(ProductViewModel::class.java)
        binding.saveBtn.setOnClickListener {
            addItemsToCart()
        }
    }

    private fun addItemsToCart(){
        val number = binding.numberOfItems.text.toString().toIntOrNull()
        if (number == null){
            val toast = Toast.makeText(requireActivity().applicationContext, "Not a number",Toast.LENGTH_SHORT)
            toast.show()
        }else if (number > 0){
            cartViewModel.addToCart(productItem,number)
        }else{
            val toast = Toast.makeText(requireActivity().applicationContext, "Wrong number",Toast.LENGTH_SHORT)
            toast.show()
        }
        dismiss()
    }
}