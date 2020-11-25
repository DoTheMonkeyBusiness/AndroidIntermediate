package com.nasalevich.androidintermidiate.mainPage.presentation.ui

import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.nasalevich.androidintermidiate.handlers.BackPressedHandler
import com.nasalevich.androidintermidiate.mainPage.presentation.MainIntent
import com.nasalevich.androidintermidiate.mainPage.presentation.MainState
import com.nasalevich.androidintermidiate.mainPage.presentation.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun MainPage(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModel: MainViewModel
) {
    val state = viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    when (val value = state.value) {
        is MainState.CatSelected -> {
            BackPressedHandler(onBackPressedDispatcher) {
                coroutineScope.launch { viewModel.userIntent.send(MainIntent.PressBack) }
            }
            EditComponent(
                viewModel = viewModel,
                onBackPressedDispatcher = onBackPressedDispatcher,
                coroutineScope = coroutineScope,
                catModel = value.cat
            )
        }
        else -> {
            ListComponent(
                viewModel = viewModel,
                coroutineScope = coroutineScope,
                state = value
            )
        }
    }
}
