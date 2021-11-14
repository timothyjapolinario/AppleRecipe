package com.example.applerecipe.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applerecipe.domain.model.Recipe
import com.example.applerecipe.presentation.ui.recipe.RecipeEvent.*
import com.example.applerecipe.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


const val STATE_KEY_RECIPE = "recipe.state.recipe.key"
@HiltViewModel
class RecipeViewModel
@Inject
    constructor(
        private val repository: RecipeRepository,
        private @Named("auth_token")val token: String,
        private val savedStateHandle: SavedStateHandle
):ViewModel(){
    val loading = mutableStateOf(false)
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    init{
        savedStateHandle.get<Int>(STATE_KEY_RECIPE)?.let{ recipeId ->
            onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeEvent){
        viewModelScope.launch {
            when(event){
                is GetRecipeEvent ->{
                    if(recipe.value == null){
                        getRecipe(event.id)
                    }
                }
            }
        }
    }

    private suspend fun getRecipe(id: Int){
        loading.value = true
        delay(1000)
        this.recipe.value = repository.get(token, id)
        savedStateHandle.set(STATE_KEY_RECIPE, id)

        loading.value = false
    }
}