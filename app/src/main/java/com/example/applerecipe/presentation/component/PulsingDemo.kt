package com.example.applerecipe.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

@Composable
fun PulsingDemo(){

    val color = MaterialTheme.colors.primary
    val infiniteTransition = rememberInfiniteTransition()


    //The animation
    val pulseMagnitude by infiniteTransition.animateFloat(
        initialValue = 40f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    //The drawing
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp),
        horizontalArrangement = Arrangement.Center )
    {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(pulseMagnitude.dp)
                .height(pulseMagnitude.dp),
            imageVector = Icons.Default.Favorite,
            contentDescription = "Fav Icon"
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .height(55.dp)
    ){
        drawCircle(
            radius = pulseMagnitude,
            brush = SolidColor(color)
        )
    }
}