package com.nasalevich.androidintermidiate.handlers

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun BackPressedHandler(dispatcher: OnBackPressedDispatcher, callback: () -> Unit) {
    DisposableEffect(Unit) {
        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callback.invoke()
            }
        }
        dispatcher.addCallback(backPressedCallback)
        onDispose {
            backPressedCallback.remove()
        }
    }
}
