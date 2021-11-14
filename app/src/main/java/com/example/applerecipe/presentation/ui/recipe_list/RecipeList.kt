package com.example.applerecipe.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applerecipe.R
import com.example.applerecipe.domain.model.Recipe
import com.example.applerecipe.presentation.component.CircularIndeterminateProgressBar
import com.example.applerecipe.presentation.component.DefaultSnackBar
import com.example.applerecipe.presentation.component.RecipeCard
import com.example.applerecipe.presentation.component.ShimmerRecipeCardItem
import com.example.applerecipe.util.SnackBarController

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeRecipePosition: (index: Int) -> Unit,
    page: Int,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    scaffoldState: ScaffoldState,
    navController: NavController,
    snackBarController: SnackBarController

){
    Box(modifier = Modifier.fillMaxSize())
    {
        if(loading){
            ShimmerRecipeCardItem(imageHeight = 250.dp)
        }
        else {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    onChangeRecipePosition(index)
                    if((index + 1)>=(page * PAGE_SIZE) && !loading){
                        Log.i("RECIPEPOSITION", index.toString())
                        onTriggerEvent(RecipeListEvent.NextPageEvent)
                    }

                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            if(recipe.id != null){
                                val bundle = Bundle()
                                bundle.putInt("recipeId", recipe.id)
                                navController.navigate(R.id.action_recipeListFragment_to_recipeFragment,bundle)
                            }
                            else{
                                snackBarController.showSnackBar(
                                    scaffoldState = scaffoldState,
                                    message = "Recipe Error",
                                    actionLabel = "Ok"
                                )
                            }
                        }
                    )
                }

            }
        }
    }
}