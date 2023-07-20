package com.example.recipeapp.models.detail

import com.google.gson.annotations.SerializedName

class ResponseSimilar : ArrayList<ResponseSimilar.ResponseSimilarItem>(){
    data class ResponseSimilarItem(
        @SerializedName("id")
        val id: Int?, // 180958
        @SerializedName("imageType")
        val imageType: String?, // jpg
        @SerializedName("readyInMinutes")
        val readyInMinutes: Int?, // 30
        @SerializedName("servings")
        val servings: Int?, // 8
        @SerializedName("sourceUrl")
        val sourceUrl: String?, // https://www.bettycrocker.com/recipes/chopped-hummus-dip-with-zaatar/b8ca2c7d-c707-40d6-bddb-08487c50bb0c
        @SerializedName("title")
        val title: String? // Chopped Hummus Dip with Za'atar
    )
}