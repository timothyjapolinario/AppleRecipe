package com.example.applerecipe.network

import com.example.applerecipe.network.model.RecipeDTO
import com.example.applerecipe.network.reponses.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeRetrofitService {

    @GET("search")
    suspend fun search(
        @Header("Authorization")token: String,
        @Query("page")page: Int,
        @Query("query")query: String
    ): RecipeSearchResponse

    @GET("get")
    suspend fun get(
        @Header("Authorization")token: String,
        @Query("id")id: Int
    ):RecipeDTO
}