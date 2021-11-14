package com.example.applerecipe.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    //should be stored in cache or datastore
    val isDark = mutableStateOf(false)
    fun toggleLightTheme(){
        isDark.value = !isDark.value
    }
}