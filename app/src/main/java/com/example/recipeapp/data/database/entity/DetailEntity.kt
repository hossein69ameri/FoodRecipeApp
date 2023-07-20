package com.example.recipeapp.data.database.entity

import com.example.recipeapp.models.detail.ResponseDetail
import com.example.recipeapp.utils.Constants
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.DETAIL_TABLE_NAME)
data class DetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val result: ResponseDetail
)
