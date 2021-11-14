package com.example.applerecipe.util

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnackBarController
constructor(
    private val scope: CoroutineScope
){
    private var snackBarJob: Job? = null

    fun showSnackBar(
        scaffoldState: ScaffoldState,
        message: String,
        actionLabel: String
    ){
        if(snackBarJob != null){
            cancelActiveJob()
        }
        snackBarJob = scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
            //resetting purposes?
            cancelActiveJob()
        }
    }

    private fun cancelActiveJob(){
        //it translates to: if not null then let job...
        snackBarJob?.let{ job ->
            job.cancel()
            snackBarJob = Job()
        }
    }
}