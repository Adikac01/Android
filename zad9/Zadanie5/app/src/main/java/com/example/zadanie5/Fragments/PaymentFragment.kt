package com.example.zadanie5.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie5.Databases.Entities.Order
import com.example.zadanie5.Databases.Entities.OrderConfirmation
import com.example.zadanie5.Network.RetrofitInstance
import com.example.zadanie5.ViewModels.OrderViewModel
import com.example.zadanie5.databinding.FragmentPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentFragment(private val combinedCost: Double): BottomSheetDialogFragment()  {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity()
        orderViewModel = ViewModelProvider(activity).get(OrderViewModel::class.java)
        binding.proceed.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.email.text.toString()
            val order = Order(
                firstName = firstName,
                lastName = lastName,
                email = email,
                cost = combinedCost
            )
            makeOrder(order){
                if(it){
                    Toast.makeText(activity, "Order created", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity,
                        "Something went wrong, please try again",
                        Toast.LENGTH_SHORT).show()
                }
            }

            orderViewModel.addOrder(order)
            dismiss()
        }
    }
    private fun makeOrder(order: Order, onResult: (Boolean)->Unit){
        val retrofit = RetrofitInstance.api
        GlobalScope.launch(Dispatchers.IO) {
            retrofit.makeOrder(order).enqueue(
                object : Callback<OrderConfirmation> {
                    override fun onResponse(
                        call: Call<OrderConfirmation>,
                        response: Response<OrderConfirmation>
                    ) {
                        println(response.toString())
                        println("=-====================================")
                        println(call.request().toString())
                        onResult(true)
                    }

                    override fun onFailure(call: Call<OrderConfirmation>, t: Throwable) {
                        onResult(false)
                    }
                }
            )
        }
    }

}