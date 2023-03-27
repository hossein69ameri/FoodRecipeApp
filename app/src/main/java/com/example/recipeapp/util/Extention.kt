package com.example.recipeapp.util

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
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

fun TextView.setDynamicallyColor(color: Int) {
    //Start - Left = 0 || Top = 1 || End - Right = 2 || Bottom = 3
    this.compoundDrawables[1].setTint(ContextCompat.getColor(context, color))
    this.setTextColor(ContextCompat.getColor(context, color))
}

fun Int.minToHour(): String {
    val time: String
    val hours: Int = this / 60
    val minutes: Int = this % 60
    time = if (hours > 0) "${hours}h:${minutes}min" else "${minutes}min"
    return time
}