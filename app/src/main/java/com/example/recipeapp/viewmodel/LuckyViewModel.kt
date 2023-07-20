package com.example.recipeapp.viewmodel

import com.example.recipeapp.data.repository.LuckyRepository
import com.example.recipeapp.models.lucky.ResponseLucky
import com.example.recipeapp.utils.Constants
import com.example.recipeapp.utils.NetworkRequest
import com.example.recipeapp.utils.NetworkResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LuckyViewModel @Inject constructor(private val repository: LuckyRepository) : ViewModel() {

    fun luckyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.API_KEY] = Constants.MY_API_KEY
        queries[Constants.NUMBER] = "1"
        queries[Constants.ADD_RECIPE_INFORMATION] = Constants.TRUE
        return queries
    }

    val luckyData = MutableLiveData<NetworkRequest<ResponseLucky>>()

    fun callLuckyApi(queries: Map<String, String>) = viewModelScope.launch {
        luckyData.value = NetworkRequest.Loading()
        val response = repository.getRandomRecipe(queries)
        luckyData.value = NetworkResponse(response).generalNetworkResponse()
    }
}