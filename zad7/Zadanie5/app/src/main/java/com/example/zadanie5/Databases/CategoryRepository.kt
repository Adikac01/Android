package com.example.zadanie5.Databases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zadanie5.Databases.Entities.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CategoryRepository {

    val database = FirebaseDatabase.getInstance("https://zadanie-5-1f926-default-rtdb.europe-west1.firebasedatabase.app")
    val categoryRef = database.getReference("categories")

    fun fetchCategories(liveData: MutableLiveData<List<Category>>){
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _categoryList: List<Category> = snapshot.children.map { dataSnapshot ->
                        Log.i("Succes", dataSnapshot.toString())
                        dataSnapshot.getValue(Category::class.java)!!

                    }
                    liveData.postValue(_categoryList)
                } catch (e: java.lang.Exception) { }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}