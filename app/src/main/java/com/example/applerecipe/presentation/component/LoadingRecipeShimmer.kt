package com.example.applerecipe.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingRecipeShimmer(
    padding:Dp = 16.dp
){
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )

    BoxWithConstraints() {

        val widthPx  = with(LocalDensity.current){(maxWidth - padding * 2f).toPx() }
        val heightPx = with(LocalDensity.current) { (250.dp - padding).toPx() }
        val gradientWidthPx = 0.3f * heightPx
        val infiniteTransition = rememberInfiniteTransition()

        //The animation
        val shimmerAnimationSpec = infiniteRepeatable<Float>(
            animation = tween(
                durationMillis = 1300,
                delayMillis = 300,
                easing = LinearEasing
            )
        )

        // Values that will change
        val yPosition = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = widthPx,
            animationSpec = shimmerAnimationSpec
        )


        //The drawing

        val brush = Brush.verticalGradient(
            colors = colors,
            startY = yPosition.value,
            endY = yPosition.value - gradientWidthPx
        )
        Column(modifier = Modifier.background(MaterialTheme.colors.background)){
            DrawLoadingRecipeShimmerCard(
                cardHeight = 250.dp,
                padding,
                4.dp,
                shimmerBrush = brush
            )
            DrawLoadingRecipeShimmerCard(
                cardHeight = 25.dp,
                padding,
                2.dp,
                shimmerBrush = brush
            )
            DrawLoadingRecipeShimmerCard(
                cardHeight = 40.dp,
                padding,
                8.dp,
                shimmerBrush = brush
            )
        }

    }
}
@Composable
fun DrawLoadingRecipeShimmerCard(
    cardHeight: Dp,
    padding: Dp,
    verticalPadding: Dp,
    shimmerBrush: Brush
) {
    Column(modifier = Modifier.padding(
        top = padding,
        start = padding,
        end= padding)) {
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.padding(vertical = verticalPadding)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .background(shimmerBrush)
                )
            }
    }
}