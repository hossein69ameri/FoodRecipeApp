package com.example.recipeapp.data.database.entity

import com.example.recipeapp.models.detail.ResponseDetail
import com.example.recipeapp.utils.Constants
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val result: ResponseDetail
)
