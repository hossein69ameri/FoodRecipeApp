package com.example.recipeapp.data.repository

import com.example.recipeapp.data.source.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class SearchRepository @Inject constructor(private val remote: RemoteDataSource) {
    suspend fun getSearchRecipe(queries: Map<String, String>) = remote.getRecipes(queries)
}