package com.example.applerecipe.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import kotlinx.coroutines.delay

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
){
    if(isDisplayed){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val progressBar = createRef()
            val topGuideLine = createGuidelineFromTop(0.3f)

            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.constrainAs(progressBar){
                    top.linkTo(topGuideLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}
//BoxWithConstraints(
//modifier = Modifier.fillMaxSize()
//) {
//    //minHeight is a property or value from BoxWithConstraints
//    val constraints =
//        if(minWidth < 600.dp){
//            //portrait mode
//            myDecoupledConstraints(0.3f)
//        }
//        else{
//            myDecoupledConstraints(0.7f)
//        }
//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize(),
//        constraintSet = constraints
//    ) {
//        CircularProgressIndicator(
//            modifier = Modifier.layoutId("progressBar"),
//            color = MaterialTheme.colors.primary
//        )
//        Text(
//            text = "Loading...",
//            style = TextStyle(
//                color = Color.Black,
//                fontSize =  15.sp
//            ),
//            modifier = Modifier.layoutId("text")
//        )
//    }
//}
//private fun myDecoupledConstraints(verticalBias: Float): ConstraintSet{
//    return ConstraintSet {
//        val guideline = createGuidelineFromTop(verticalBias)
//        val progressBar = createRefFor("progressBar")
//        val text = createRefFor("text")
//
//        constrain(progressBar){
//            top.linkTo(guideline)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//        constrain(text){
//            top.linkTo(progressBar.bottom)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//    }
//}
// -------------------------------------------------
//Row(modifier = Modifier
//.fillMaxWidth()
//.padding(20.dp),
//horizontalArrangement = Arrangement.Center){
//    CircularProgressIndicator(
//        color = MaterialTheme.colors.primary
//    )
//
//}