package com.example.recipeapp.data.source

import com.example.recipeapp.data.network.ApiServices
import com.example.recipeapp.model.BodyRegister
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: ApiServices) {

    suspend fun postRegister(apiKey: String, body: BodyRegister) = api.postRegister(apiKey, body)

}