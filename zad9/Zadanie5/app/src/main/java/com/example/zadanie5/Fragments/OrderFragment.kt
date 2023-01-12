package com.example.zadanie5.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie5.Adapters.CartAdapter
import com.example.zadanie5.Adapters.OrderAdapter
import com.example.zadanie5.R
import com.example.zadanie5.ViewModels.OrderViewModel
import com.example.zadanie5.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        orderViewModel = ViewModelProvider(activity).get(OrderViewModel::class.java)
        setRecyclerView()
    }
    private fun setRecyclerView()
    {
        val orderFragment = this
        orderViewModel.orders.observe(viewLifecycleOwner){
            binding.orderRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = it?.let { it1 -> OrderAdapter(it) }
            }
        }
    }

}