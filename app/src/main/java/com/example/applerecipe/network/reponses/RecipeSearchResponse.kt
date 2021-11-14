package com.example.applerecipe.network.reponses

import com.example.applerecipe.network.model.RecipeDTO
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse (

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDTO>
)