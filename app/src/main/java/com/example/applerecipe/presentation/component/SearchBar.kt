package com.example.applerecipe.presentation.ui

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.applerecipe.presentation.component.FoodCategoryChip
import com.example.applerecipe.presentation.ui.recipe_list.FoodCategory
import kotlinx.coroutines.launch

@Composable
@ExperimentalComposeUiApi
fun SearchBar(
    query: String,
    onQueryChanged: (newValue: String)->Unit,
    newSearch: ()->Unit,
    foodCategories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged:(category:String)->Unit,
    onToggleTheme: () -> Unit
    //onChangeCategoryScrollPosition:(scrollPosition: Int)->Unit,
    //scrollPosition: Int
){
    Log.i("RECOMPOSED:","YEAH!")
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp
    ){
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = {newValue->
                        onQueryChanged(newValue)
                    },
                    label = {
                        Text(text = "Search",)
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            newSearch()
                            keyboardController?.hide()
                        }
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null)
                    },
                    textStyle=MaterialTheme.typography.button,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface,
                    )
                )

                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        onClick = onToggleTheme,
                        modifier = Modifier
                                .constrainAs(menu)
                            {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                        ) {
                            Icon(Icons.Filled.MoreVert,contentDescription = null)
                    }
                }
            }


            //scope.launch { scrollState.scrollTo(scrollPosition) }


            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                )
            {
                itemsIndexed(
                    items = foodCategories,
                ) { index, foodCategory ->
                    FoodCategoryChip(
                        category = foodCategory.name,
                        isSelected = foodCategory == selectedCategory,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                            //onChangeCategoryScrollPosition(scrollState.value)
                        },
                        onExecuteSearch = newSearch
                    )
                }
            }

        }
    }
}