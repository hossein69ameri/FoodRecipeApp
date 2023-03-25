package com.example.recipeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.RegisterRepository
import com.example.recipeapp.model.BodyRegister
import com.example.recipeapp.model.ResponseRegister
import com.example.recipeapp.util.NetworkRequest
import com.example.recipeapp.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository : RegisterRepository) : ViewModel() {

    //Api
    val registerData = MutableLiveData<NetworkRequest<ResponseRegister>>()
    fun callRegisterApi(apiKey: String, body: BodyRegister) = viewModelScope.launch {
        registerData.value = NetworkRequest.Loading()
        val response = repository.postRegister(apiKey, body)
        registerData.value = NetworkResponse(response).generalNetworkResponse()
    }
    //data store
    fun saveRegisterData(username : String,hash : String) = viewModelScope.launch { repository.saveRegisterData(username, hash) }
    val readRegisterData = repository.readRegisterData

}