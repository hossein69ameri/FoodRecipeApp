package com.example.recipeapp.data.network

import com.example.recipeapp.models.detail.ResponseDetail
import com.example.recipeapp.models.detail.ResponseSimilar
import com.example.recipeapp.models.lucky.ResponseLucky
import com.example.recipeapp.models.recipe.ResponseRecipes
import com.example.recipeapp.models.register.BodyRegister
import com.example.recipeapp.models.register.ResponseRegister
import com.example.recipeapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    
    @POST("users/connect")
    suspend fun postRegister(@Query(API_KEY) apiKey: String, @Body body: BodyRegister): Response<ResponseRegister>

    @GET("recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>): Response<ResponseRecipes>

    @GET("recipes/{id}/information")
    suspend fun getDetail(@Path("id") id: Int, @Query(API_KEY) apiKey: String): Response<ResponseDetail>

    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(@Path("id") id: Int, @Query(API_KEY) apiKey: String): Response<ResponseSimilar>

    @GET("recipes/random")
    suspend fun getRandomRecipe(@QueryMap queries: Map<String, String>): Response<ResponseLucky>
}