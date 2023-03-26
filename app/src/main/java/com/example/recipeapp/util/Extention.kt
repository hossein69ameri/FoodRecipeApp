package com.example.recipeapp.util

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message : String){
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}

fun RecyclerView.setupRecyclerView(layoutManager2: RecyclerView.LayoutManager,adapter2: RecyclerView.Adapter<*>){
    this.apply {
        layoutManager = layoutManager2
        adapter= adapter2
        setHasFixedSize(true)

    }
}