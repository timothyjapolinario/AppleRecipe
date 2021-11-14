package com.example.applerecipe.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.applerecipe.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
//When you have a fragment that needs to be injected something, then the
//activity attach to it must also have an @AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.green_400)
        setContentView(R.layout.activity_main)
    }
}
//        val service = Retrofit.Builder()
////            .baseUrl("https://food2fork.ca/api/recipe/")
////            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
////            .build()
////            .create(RecipeRetrofitService::class.java)
////
////        CoroutineScope(IO).launch {
////            val recipe = service.get(
////                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
////                id = 583
////            )
////            Log.i("Recipe: ", "The recipe: ${recipe.title}")
////        }



//        val mapper = RecipeNetworkMapper()
//
//        val recipe = Recipe()
//
//        val networkEntity: RecipeNetworkEntity = mapper.mapToEntity(recipe)
//        val r: Recipe = mapper.mapFromEntity(networkEntity)


//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, RecipeListFragment())
//            .commit()













//        setContent {
//            Column(
//                modifier = Modifier
//                    .background(color = Color(0x7d7b77F2))
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.happy_meal_small),
//                    //MODIFIER AND CONTENTSCALE Scales the images.
//                    modifier = Modifier
//                        .height(300.dp)
//                        .align(Alignment.CenterHorizontally),
//
//                    contentScale = ContentScale.Crop,
//                    contentDescription ="happy_meal_small"
//                )
//                Column(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxHeight(),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Row(
//                        //To have space between, you have to fill the max width
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = "Happy Meal",
//                            style = TextStyle(
//                                fontSize = 30.sp,
//                                color = Color.White
//                            )
//                        )
//                        Text(
//                            text = "$5.99",
//                            style = TextStyle(
//                                fontSize =  20.sp,
//                                color = Color.LightGray
//                            ),
//                            modifier = Modifier.align(Alignment.CenterVertically)
//                        )
//                    }
//                    Spacer(modifier = Modifier.padding(top=10.dp))
//                    //HexColor: get hex -> add 0x at the start then cc at the end.
//                    Text(text = "800 Calories", color = Color.Red)
//                    Spacer(modifier = Modifier.padding(top=10.dp))
//                    Button(
//                        onClick = { Log.i("Button","Clicked!") },
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                            .background(color = Color.Green)) {
//                                Text(
//                                    text = "ORDER NOW",
//                                    style = TextStyle(
//                                        fontSize = 30.sp,
//                                        color = Color.White
//                                    )
//                                )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    AppleRecipeTheme {
//        Greeting("cat")
//
