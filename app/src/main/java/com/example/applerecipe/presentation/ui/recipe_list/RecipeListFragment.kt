package com.example.applerecipe.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.ComposeView

import androidx.compose.ui.unit.dp

import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.applerecipe.domain.model.Recipe

import com.example.applerecipe.presentation.BaseApplication
import com.example.applerecipe.presentation.component.*
import com.example.applerecipe.presentation.theme.AppTheme
import com.example.applerecipe.presentation.ui.SearchBar
import com.example.applerecipe.util.SnackBarController



import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalComposeUiApi
class RecipeListFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    //to share viewModels between fragments do use "activityViewModels()
    //val viewModel: RecipeListViewModel by activityViewModels()


    private val snackBarController = SnackBarController(lifecycleScope)

    val viewModel: RecipeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //This is just to check that the model is in the same memory location address which indicates
        //they are the same or the data is persisted.
        Log.i("FRAGMENT:", viewModel.getRandomString())
        Log.i("FRAGMENT:", viewModel.getRepo().toString())
        Log.i("FRAGMENT:", viewModel.getToken())
    }

   
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val query = viewModel.query.value
                val recipes = viewModel.recipes.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                val foodCategories = getAllFoodCategories()
                val scaffoldState = rememberScaffoldState()
                val page = viewModel.page.value
                AppTheme(
                    darkTheme = application.isDark.value,
                    displayProgressBar = loading,
                    scaffoldState = scaffoldState
                ) {
                    Scaffold(
                        topBar = {
                            SearchBar(
                                query = query,
                                onQueryChanged = {viewModel.onQueryChanged(it)},
                                newSearch = {viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)},
                                foodCategories = foodCategories ,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = {viewModel.onSelectedCategoryChanged(it)},
                                onToggleTheme = {
                                    application.toggleLightTheme()
                                    lifecycleScope.launch{
                                        snackBarController.showSnackBar(
                                            scaffoldState = scaffoldState,
                                            message ="Changed to ${if(application.isDark.value)"Dark" else "Light"} Mode",
                                            actionLabel = "Change Theme Mode",
                                        )
                                    }
                                }

                                //scrollPosition = viewModel.categoryScrollPosition,
                                //onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition
                            )
                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ){
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            onChangeRecipePosition = {
                                viewModel.onChangeRecipePosition(it)
                            },
                            page = page,
                            onTriggerEvent = {
                                 viewModel.onTriggerEvent(it)
                            },
                            scaffoldState = scaffoldState,
                            navController = findNavController(),
                            snackBarController = snackBarController
                        )
                    }
                }
            }
        }
    }
}


//DEMONSTRATING INTEROPERABILITY FROM LAYOUT TO COMPOSE
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View{
//        val view = ComposeView(requireContext())
//        view.apply {
//            setContent {
//                Text(text = "Hey look a composable",
//                    color = Color.White)
//            }
//        }
//        return view
//    }
//}