package com.example.recipeapp.viewmodel

import com.example.recipeapp.data.repository.MenuRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {

    fun saveToStore(meal: String, mealId: Int, diet: String, dietId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveMenuData(meal, mealId, diet, dietId)
    }

    val readMenuStoredItems = repository.readMenuData

    fun mealsList(): MutableList<String> {
        return mutableListOf(
            "Main Course", "Bread", "Marinade", "Side Dish", "Breakfast", "Dessert", "Soup", "Snack", "Appetizer",
            "Beverage", "Drink", "Salad", "Sauce"
        )
    }

    fun dietsList(): MutableList<String> {
        return mutableListOf("Gluten Free", "Ketogenic", "Vegetarian", "Vegan", "Pescetarian", "Paleo")
    }

}