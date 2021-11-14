package com.example.applerecipe.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DefaultSnackBar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
    onDismiss: () -> Unit
) {

    SnackbarHost(
        modifier = modifier,
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            Snackbar(

                action = {
                    TextButton(
                        onClick = {
                            onDismiss()
                        }
                    )
                    {
                        Text(
                            text = "Hide",
                            style = TextStyle(color = MaterialTheme.colors.surface)
                        )
                    }
                }
            ){
                Text(
                    text= snackbarHostState.currentSnackbarData?.message?:"",
                    style = TextStyle(color = MaterialTheme.colors.surface)
                )
            }
        }
    )
}