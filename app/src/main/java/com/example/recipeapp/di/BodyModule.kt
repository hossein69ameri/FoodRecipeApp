package com.example.recipeapp.di

import com.example.recipeapp.model.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object BodyModule {

    @Provides
    fun bodyProvides() = BodyRegister()
}