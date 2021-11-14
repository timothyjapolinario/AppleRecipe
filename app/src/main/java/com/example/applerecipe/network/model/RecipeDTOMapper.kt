package com.example.applerecipe.network.model

import com.example.applerecipe.domain.model.Recipe
import com.example.applerecipe.domain.util.DomainMapper

class RecipeDTOMapper : DomainMapper<RecipeDTO, Recipe>{

    override fun mapToDomainModel(model: RecipeDTO): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featured_image,
            rating = model.rating,
            publisher =  model.publisher,
            sourceUrl =  model.sourceUrl,
            description = model.description,
            cookingInstruction = model.cooking_instruction,
            //if ingredients is null, then create an empty list
            ingredients = model.ingredients?: listOf(),
            dateAdded = model.date_added,
            dateUpdated = model.date_updated

        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDTO {
        return RecipeDTO(
            pk = domainModel.id,
            title = domainModel.title,
            featured_image = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher =  domainModel.publisher,
            sourceUrl =  domainModel.sourceUrl,
            description = domainModel.description,
            cooking_instruction = domainModel.cookingInstruction,
            //if ingredients is null, then create an empty list
            ingredients = domainModel.ingredients?: listOf(),
            date_added = domainModel.dateAdded,
            date_updated = domainModel.dateUpdated

        )
    }
    //Converts list of entity to a list of recipe
    //map is like a forloop that takes a lambda then applies it to each item in the list.
    fun toDomainList(initial: List<RecipeDTO>):List<Recipe>{
        return initial.map{
            mapToDomainModel(it)
        }
    }

    fun fromDomainList(initial: List<Recipe>):List<RecipeDTO>{
        return initial.map {
            mapFromDomainModel(it)
        }
    }

}