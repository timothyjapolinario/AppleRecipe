package com.example.applerecipe.di

import com.example.applerecipe.network.RecipeRetrofitService
import com.example.applerecipe.network.model.RecipeDTOMapper
import com.example.applerecipe.repository.RecipeRepository
import com.example.applerecipe.repository.RecipeRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeRetrofitService,
        recipeDTOMapper: RecipeDTOMapper
    //As you can see below, it indicates that it must return a RecipeRepository which is
    //an interface for RecipeRepositoryImplementation.
    //But we returned a RecipeRepositoryImplementation.
    //The explanation here is for testing.
    //So it can be easily change when we want to test our app without using the RetrofitService.
    ):RecipeRepository{
        return RecipeRepositoryImplementation(recipeService, recipeDTOMapper)
    }
}