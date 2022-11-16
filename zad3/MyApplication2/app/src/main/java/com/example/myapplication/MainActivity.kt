package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private var canAddOperation = false
    private var canAddNumber = true
    private var canAddDecimal = true
    private var exp : String = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun numberEvent(view : View){
        if(view is Button && canAddNumber){
           if(view.text == "."){
               if(canAddDecimal){
                   exp+=view.text
                   binding.res.append(view.text)
               }
               canAddDecimal = false
           }
            else {
               binding.res.append(view.text)
               exp += view.text
               canAddOperation = true
           }
        }
    }
    fun operatorEvent(view: View){
        if(view is Button && canAddOperation){
            binding.res.append(view.text)
            exp += view.text
            canAddOperation = false
            canAddNumber = true
            canAddDecimal = true
        }
    }
    fun clearEvent(view: View){
        binding.res.text=""
        exp = ""
        canAddNumber = true
        canAddDecimal = true
    }
    fun equalsEvent(view: View) {
        try {
            canAddNumber = false
            canAddDecimal = false
            val input = ExpressionBuilder(exp).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()){
                binding.res.text = longOutput.toString()
                exp = longOutput.toString()
            }else{
                binding.res.text = output.toString()
                exp = output.toString()
            }

        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }
}