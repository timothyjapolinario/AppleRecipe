package com.example.applerecipe.presentation.component

import android.view.animation.Animation
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applerecipe.R
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun AnimatedHeartButton(){

    val heartState = remember {mutableStateOf(HeartButtonState.IDLE)}
    val transition = updateTransition(targetState = heartState)

    val pulseMagnitude = transition.animateFloat(
        transitionSpec = {tween(3000, easing = FastOutSlowInEasing)},
        label = "pulseMagnitude"
    ) {
        if(it.value == HeartButtonState.IDLE) 10f else 50f
    }
    Row(modifier = Modifier
        .padding(top = 100.dp)
        .fillMaxWidth()
        .height(55.dp),
        horizontalArrangement = Arrangement.Center )
    {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(pulseMagnitude.value.dp)
                .height(pulseMagnitude.value.dp),
            painter = rememberCoilPainter(request = R.drawable.heart_red),
            contentDescription = "Fav Icon"
        )
    }
    heartState.value = HeartButtonState.ACTIVE
}
private enum class HeartButtonState {
    IDLE, ACTIVE
}

