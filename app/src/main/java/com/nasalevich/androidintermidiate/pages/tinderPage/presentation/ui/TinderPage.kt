package com.nasalevich.androidintermidiate.pages.tinderPage.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.nasalevich.androidintermidiate.base.ui.Progress
import com.nasalevich.androidintermidiate.pages.tinderPage.presentation.TinderIntent
import com.nasalevich.androidintermidiate.pages.tinderPage.presentation.TinderState
import com.nasalevich.androidintermidiate.pages.tinderPage.presentation.TinderViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun TinderPage(viewModel: TinderViewModel = getViewModel()) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val onSwipeLeft: () -> Unit = {
        coroutineScope.launch {
            viewModel.userIntent.send(TinderIntent.Dislike)
        }
    }
    val onSwipeRight: () -> Unit = {
        coroutineScope.launch {
            viewModel.userIntent.send(TinderIntent.Like)
        }
    }

    when (val value = state) {
        is TinderState.Idle -> coroutineScope.launch {
            viewModel.userIntent.send(TinderIntent.FetchData)
        }
        is TinderState.Loading -> Progress(modifier = Modifier.fillMaxSize())
        is TinderState.Error -> CardMessageView(
            text = "Error loading data",
            modifier = Modifier.fillMaxSize(),
        )
        is TinderState.Success -> CardStack(
            items = value.characters,
            onSwipeLeft = onSwipeLeft,
            onSwipeRight = onSwipeRight,
        )
    }
}
