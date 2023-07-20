package com.example.recipeapp.viewmodel

import com.example.recipeapp.data.repository.FavoriteRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(repository: FavoriteRepository) : ViewModel() {
    val readFavoriteData = repository.local.loadFavorites().asLiveData()
}