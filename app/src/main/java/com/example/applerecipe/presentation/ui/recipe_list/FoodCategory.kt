package com.example.applerecipe.presentation.ui.recipe_list

import com.example.applerecipe.presentation.ui.recipe_list.FoodCategory.*

enum class FoodCategory(value: String){

    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}
fun  getFoodCategory(value: String): FoodCategory?{
    //Creates a map
    val map = FoodCategory.values().associateBy(FoodCategory::name)
    return map[value]
}