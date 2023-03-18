package com.example.recipeapp.data.network


import com.example.recipeapp.model.BodyRegister
import com.example.recipeapp.model.ResponseRegister
import com.example.recipeapp.util.Constants
import com.example.recipeapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @POST("users/connect")
    suspend fun postRegister(
        @Query(API_KEY) apiKey: String,
        @Body body: BodyRegister
    ): Response<ResponseRegister>


}