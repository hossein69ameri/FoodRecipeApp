package com.example.recipeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.model.ResponseRecipes
import com.example.recipeapp.util.Constants
import com.example.recipeapp.util.NetworkRequest
import com.example.recipeapp.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    //queries
    fun popularQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.API_KEY] = Constants.SITE_API_KEY
        queries[Constants.SORT] = Constants.POPULARITY
        queries[Constants.NUMBER] = Constants.LIMITED_COUNT.toString()
        queries[Constants.ADD_RECIPE_INFORMATION] = Constants.TRUE
        return queries
    }

    //popular
    val popularData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callPopularApi(queries : Map<String,String>) = viewModelScope.launch {
        popularData.value = NetworkRequest.Loading()
        val response = repository.remote.getRecipes(queries)
        popularData.value = NetworkResponse(response).generalNetworkResponse()
    }
}