package com.example.zadanie5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var productsList = mutableListOf(
            ProductItem("Klawiatura", "Jakas klawiatura"),
            ProductItem("Myszka", "Jakas myszka"),
            ProductItem("Laptop", "Jakis laptop"),
            ProductItem("Mikrofon", "Jakis dobry mikrofon")
        )

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.productItems.value = productsList
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        productViewModel.productItems.observe(this){
            binding.productsRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = ProductsAdapter(it, mainActivity)
            }
        }
    }

    override fun displayDescription(productItem: ProductItem) {
        val desc = productItem.desc
        Intent(this,DescriptionActivity::class.java).also{
            it.putExtra("EXTRA_DESC", desc)
            startActivity(it)
        }
    }
}