package com.example.applerecipe.presentation.ui.recipe_list

//To inject something on a viewmodel, do not use @AndroidEntryPoint\
//But use @HiltViewModel and @Inject

import android.util.Log
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applerecipe.domain.model.Recipe
import com.example.applerecipe.network.model.RecipeDTOMapper
import com.example.applerecipe.presentation.ui.recipe_list.RecipeListEvent.*
import com.example.applerecipe.repository.RecipeRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
const val PAGE_SIZE = 30
const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"


@HiltViewModel
class RecipeListViewModel
//a construction injection
@Inject
constructor(
    private val randomString: String,
    private val repository: RecipeRepository,
    private @Named("auth_token")val token: String,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    //When using mutableState, declare it on a composable(in this project, its in RecipeListFragment)
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val query = mutableStateOf("Chicken")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Int = 0
    val loading = mutableStateOf(false)
    var recipePosition: Int = 0
    val page = mutableStateOf(1)
    init{
        if(savedStateHandle.get<String>(STATE_KEY_QUERY) == null){
            Log.i("MYOWNQUERYSTATEINIT:","null")
        }else{
            Log.i("MYOWNQUERYSTATEINIT:",savedStateHandle.get(STATE_KEY_QUERY)!!)
        }

        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { page ->
            setPage(page)
        }

        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { query ->
            setQuery(query)
        }

        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { category ->
            setSelectedCategory(category)
        }

        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { scrollPosition ->
            setListScrollPosition(scrollPosition)
        }
        if(recipePosition != 0){
            onTriggerEvent(RestoreStateEvent)
        }
        else {
            onTriggerEvent(NewSearchEvent)
        }
    }

    fun onTriggerEvent(event: RecipeListEvent){
        viewModelScope.launch {
            try{
                when(event){
                    is NewSearchEvent -> {
                        newSearch()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }

                }

            }
            catch(e: Exception){
                Log.e("ERROR", "onTriggerEvent: Exception: ${e}, ${e.cause}")
            }

        }
    }

    private suspend fun newSearch(){

            loading.value = true
            resetSearchState()
            delay(2000)
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            loading.value = false
            recipes.value = result

    }

    private suspend  fun nextPage(){
            Log.i("RECIPEPOSITION", "nextpage passed")
            if((recipePosition + 1) >= (page.value * PAGE_SIZE)) {
                Log.i("RECIPEPOSITION", "viewmodelcondition passed")
                loading.value = true
                incrementPage()

                val result = repository.search(
                    token = token,
                    page = page.value,
                    query = query.value
                )
                delay(1000)
                appendNewRecipes(result)
            }
            loading.value = false
    }

    fun incrementPage(){
        setPage(page.value + 1)
    }

    fun onChangeRecipePosition(position: Int){
        setListScrollPosition(position)
    }

    fun appendNewRecipes(newRecipes: List<Recipe>){
        val currentRecipes = ArrayList(recipes.value)
        currentRecipes.addAll(newRecipes)
        this.recipes.value = currentRecipes
    }
    fun resetSearchState(){
        recipes.value = listOf()
        page.value = 1
        onChangeRecipePosition(0)
        if(query.value != selectedCategory.value?.name){

            clearCategorySelected()
        }
    }

    fun clearCategorySelected(){
        setSelectedCategory(null)
        selectedCategory.value = null
    }
    fun onQueryChanged(query: String){
        setQuery(query = query)
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int){
        categoryScrollPosition = position
    }


    fun getRepo()  = repository
    fun getRandomString() = randomString
    fun getToken() = token

    private fun setListScrollPosition(position: Int){
        recipePosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, page)
    }

    private fun setSelectedCategory(category: FoodCategory?){
        this.selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun setPage(page: Int){
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setQuery(query: String){
        this.query.value = query
        Log.i("MYOWNQUERRY:",query)
        savedStateHandle.set(STATE_KEY_QUERY, query)
        Log.i("MYOWNQUERYSTATE:",savedStateHandle.get(STATE_KEY_QUERY)!!)
    }

    private suspend fun restoreState(){
        Log.i("STAAATE:", "Restored!")
        loading.value = true
        val results: MutableList<Recipe> = mutableListOf()

        for(p in 1..page.value){
            val result = repository.search(
                token = token,
                page = p,
                query = query.value
            )
            results.addAll(result)
            if(p == page.value){
                recipes.value = results
                loading.value = false
            }
        }

    }
}