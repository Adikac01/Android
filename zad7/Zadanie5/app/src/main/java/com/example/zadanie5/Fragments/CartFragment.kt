package com.example.zadanie5.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie5.Adapters.CartAdapter
import com.example.zadanie5.Databases.Entities.CartItem
import com.example.zadanie5.ViewModels.CartViewModel
import com.example.zadanie5.databinding.FragmentCartBinding

class CartFragment: Fragment(), CartClickListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        cartViewModel = ViewModelProvider(activity).get(CartViewModel::class.java)
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val cartFragment = this
        cartViewModel.cartItems.observe(viewLifecycleOwner){
            binding.cartRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = it?.let { it1 -> CartAdapter(it1,cartFragment) }
            }
        }
    }

    override fun removeFromCart(cartItem: CartItem) {
        cartViewModel.removeFromCart(cartItem)
    }


}