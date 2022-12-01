package com.example.zadanie5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie5.Cart.CartFragment
import com.example.zadanie5.Cart.CartViewModel
import com.example.zadanie5.Products.*
import com.example.zadanie5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cartViewModel: CartViewModel

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