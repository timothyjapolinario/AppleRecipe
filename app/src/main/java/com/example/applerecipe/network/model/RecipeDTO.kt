package com.example.applerecipe.network.model

import com.google.gson.annotations.SerializedName


//"data" in data class just means that its just a data. Its for naming convention
data class RecipeDTO(
    //Serialized Name is not needed if the var name is the same as the key from the json from the api.
    //In this case, we use SerializedName, therefore, we can change the var name like to primaryKey.
    @SerializedName("pk")
    var pk: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("featured_image")
    var featured_image: String? = null,

    @SerializedName("publisher")
    var publisher: String? = null,

    @SerializedName("rating")
    var rating: Int? = null,

    @SerializedName("source_url")
    var sourceUrl: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("cooking_instruction")
    var cooking_instruction: String? = null,

    @SerializedName("ingredients")
    var ingredients: List<String>? = null,

    @SerializedName("date_added")
    var date_added: String? = null,

    @SerializedName("date_updated")
    var date_updated: String? = null,

)