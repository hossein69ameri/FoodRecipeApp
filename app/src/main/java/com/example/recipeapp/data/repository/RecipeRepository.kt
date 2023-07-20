package com.example.recipeapp.data.repository

import com.example.recipeapp.data.source.LocalDataSource
import com.example.recipeapp.data.source.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RecipeRepository @Inject constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) {
    val remote = remoteDataSource
    val local = localDataSource
}