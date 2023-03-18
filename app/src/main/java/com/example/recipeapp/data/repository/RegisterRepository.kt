package com.example.recipeapp.data.repository

import com.example.recipeapp.data.source.RemoteDataSource
import com.example.recipeapp.model.BodyRegister
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RegisterRepository @Inject constructor(private val remote: RemoteDataSource) {
    suspend fun postRegister(apiKey: String, body: BodyRegister) = remote.postRegister(apiKey, body)
}