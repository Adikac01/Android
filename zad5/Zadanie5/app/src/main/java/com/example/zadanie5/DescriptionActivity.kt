package com.example.zadanie5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zadanie5.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val desc = intent.getStringExtra("EXTRA_DESC")
        binding.textView.text = desc
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}