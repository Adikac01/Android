package com.example.zadanie5.Databases

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zadanie5.Databases.Entities.Category
import com.example.zadanie5.Databases.Entities.ProductItem
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.protobuf.Value
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.coroutineContext

class ProductItemRepository() {


    val database = FirebaseDatabase.getInstance("https://zadanie-5-1f926-default-rtdb.europe-west1.firebasedatabase.app")
    val productsRef = database.getReference("products")

    fun fetchItems(liveData: MutableLiveData<List<ProductItem>>){
        productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _productList: List<ProductItem> = snapshot.children.map { dataSnapshot ->
                        Log.i("Succes", dataSnapshot.toString())

                        dataSnapshot.getValue(ProductItem::class.java)!!

                    }
                    liveData.postValue(_productList)
                } catch (e: java.lang.Exception) { }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("can", error.toString())
            }

        })

    }

}