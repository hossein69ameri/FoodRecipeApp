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
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    //---Popular---//
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

    //---Recent---//
    //queries
    fun recentQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.API_KEY] = Constants.SITE_API_KEY
        queries[Constants.TYPE] = Constants.MAIN_COURSE
        queries[Constants.DIET] = Constants.GLUTEN_FREE
        queries[Constants.NUMBER] = Constants.FULL_COUNT.toString()
        queries[Constants.ADD_RECIPE_INFORMATION] = Constants.TRUE
        return queries
    }

    //popular
    val recentData = MutableLiveData<NetworkRequest<ResponseRecipes>>()
    fun callRecentApi(queries : Map<String,String>) = viewModelScope.launch {
        recentData.value = NetworkRequest.Loading()
        val response = repository.remote.getRecipes(queries)
        recentData.value = recentNetworkResponse(response)
    }

    private fun recentNetworkResponse(response: Response<ResponseRecipes>): NetworkRequest<ResponseRecipes> {
        return when {
            response.message().contains("timeout") -> NetworkRequest.Error("Timeout")
            response.code() == 401 -> NetworkRequest.Error("You are not authorized")
            response.code() == 402 -> NetworkRequest.Error("Your free plan finished")
            response.code() == 422 -> NetworkRequest.Error("Api key not found!")
            response.code() == 500 -> NetworkRequest.Error("Try again")
            response.isSuccessful -> NetworkRequest.Success(response.body()!!)
            response.body()!!.results.isNullOrEmpty() -> NetworkRequest.Error("Not Found any Recipe!")
            else -> NetworkRequest.Error(response.message())
        }
    }


}