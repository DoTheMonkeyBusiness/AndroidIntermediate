package com.nasalevich.androidintermidiate.pages.mainPage.presentation.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.nasalevich.androidintermidiate.handlers.BackPressedHandler
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainIntent
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainState
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@Composable
fun MainPage(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModel: MainViewModel = getViewModel()
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
