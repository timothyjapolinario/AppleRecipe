package com.example.applerecipe.repository

import com.example.applerecipe.domain.model.Recipe
import com.example.applerecipe.network.RecipeRetrofitService
import com.example.applerecipe.network.model.RecipeDTOMapper

class RecipeRepositoryImplementation(
    private val recipeService: RecipeRetrofitService,
    private val mapper: RecipeDTOMapper
):RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe>
    {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token, id))
    }
}