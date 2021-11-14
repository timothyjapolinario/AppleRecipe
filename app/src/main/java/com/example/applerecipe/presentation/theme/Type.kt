package com.example.applerecipe.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.applerecipe.R

private val Alegarya = FontFamily(
    Font(R.font.alegreya_black, FontWeight.W300),
    Font(R.font.alegreya_regular, FontWeight.W400),
    Font(R.font.alegreya_medium, FontWeight.W500),
    Font(R.font.alegreya_bold, FontWeight.W600)
)

val AlegaryaTypography = Typography(
    h1 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Alegarya,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
    ),
    caption = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Alegarya,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )

)