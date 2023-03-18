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
class RegisterViewModel @Inject constructor(private val register: RegisterRepository) : ViewModel() {

     val registerLiveData = MutableLiveData<NetworkRequest<ResponseRegister>>()

    fun callRegisterApi(apikey:String,body: BodyRegister) = viewModelScope.launch {
        registerLiveData.value = NetworkRequest.Loading()
        val response = register.postRegister(apikey,body)
        registerLiveData.value = NetworkResponse(response).generalNetworkResponse()

    }

}