package com.example.applerecipe.presentation.ui.recipe

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.applerecipe.domain.model.Recipe
import com.example.applerecipe.presentation.component.LoadingRecipeShimmer
import com.google.accompanist.coil.rememberCoilPainter


const val IMAGE_HEIGHT = 260
@Composable
fun RecipeView(
    loading: Boolean,
    recipe: Recipe?,
){
    val recipeScrollState: ScrollState = rememberScrollState(1)
    Column(
        modifier = Modifier
            .verticalScroll(recipeScrollState)
            .fillMaxWidth()
    ) {
        if(loading || recipe == null){
            LoadingRecipeShimmer()
        }else{
            recipe.featuredImage?.let{url->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(IMAGE_HEIGHT.dp)
                        .padding(bottom = 6.dp),
                    painter = rememberCoilPainter(request = url),
                    contentDescription = "recipeImage",
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier =
            Modifier.padding(
                start = 8.dp,
                end = 5.dp
            )
            ){
                Row(
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(.85f)
                            .wrapContentWidth(align = Alignment.Start),
                        text = recipe.title?.let{
                            recipe.title
                        }?:"Unknown Title",
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(alignment = Alignment.CenterVertically,)
                            .wrapContentWidth(Alignment.End),
                        text = recipe.rating!!.toString()
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(bottom = 30.dp),
                    text = recipe.dateUpdated?.let{dateUpdated ->
                        "Updated ${dateUpdated} by ${recipe.publisher}"
                    }?:"By ${recipe.publisher}",
                    style = MaterialTheme.typography.h6
                )
                for(ingredients in recipe.ingredients!!){
                    Text(
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp
                        ),
                        text = ingredients,
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
    }
}