package com.example.loginandregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loginandregistration.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confPassword = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confPassword.isNotEmpty()){

                if (pass == confPassword){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                            Log.i("Error", it.exception.toString())

                        }
                    }

                }else{
                    Toast.makeText(this, "Passwords are not matching", Toast.LENGTH_SHORT).show()
                }

            }else{
                if(email.isEmpty()){
                    Toast.makeText(this,"Email field is empty", Toast.LENGTH_SHORT).show()
                }else if (pass.isEmpty()){
                    Toast.makeText(this,"Password field is empty", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Confirm the password!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}