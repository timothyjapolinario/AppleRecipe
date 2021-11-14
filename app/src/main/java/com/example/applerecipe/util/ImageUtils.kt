package com.example.applerecipe.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.Image
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.LoadPainter


@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImage: Int
): LoadPainter<Any>{
    return rememberCoilPainter(request = url, previewPlaceholder = defaultImage)
}
