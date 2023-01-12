package com.example.zadanie5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie5.Fragments.CartFragment
import com.example.zadanie5.Fragments.CategoryFragment
import com.example.zadanie5.Fragments.OrderFragment
import com.example.zadanie5.ViewModels.CartViewModel
import com.example.zadanie5.Fragments.ProductFragment
import com.example.zadanie5.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cartViewModel: CartViewModel
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        loadFragment(ProductFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.miProducts -> {
                    loadFragment(ProductFragment())
                }
                R.id.miCart -> {
                    loadFragment(CartFragment())
                }
                R.id.miCategories -> {
                    loadFragment(CategoryFragment())
                }
                R.id.miOrders -> {
                    loadFragment(OrderFragment())
                }
            }
            true
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}