package com.nasalevich.androidintermidiate.pages.mainPage.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nasalevich.androidintermidiate.base.ui.Progress
import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainIntent
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainState
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun ListComponent(
    viewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    state: MainState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "MVI Architecture")
                }
            )
        }
    ) { innerPadding ->
        BodyContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            coroutineScope = coroutineScope,
            state = state
        )
    }
}

@ExperimentalCoroutinesApi
@Composable
private fun BodyContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    state: MainState
) {
    Column(
        modifier = modifier
    ) {
        when (state) {
            is MainState.Idle -> {
                coroutineScope.launch {
                    viewModel.userIntent.send(MainIntent.FetchCats)
                }
            }
            is MainState.Success -> {
                ItemsList(
                    items = state.cats,
                    viewModel = viewModel,
                    coroutineScope = coroutineScope
                )
            }
            else -> Progress(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ItemsList(
    items: List<CatModel>,
    viewModel: MainViewModel,
    coroutineScope: CoroutineScope
) {
    LazyColumn {
        items(items = items, itemContent = { it: CatModel ->
            ListItem(
                catModel = it,
                viewModel = viewModel,
                coroutineScope = coroutineScope,
            )
            Divider()
        })
    }
}

@Composable
private fun ListItem(
    catModel: CatModel,
    viewModel: MainViewModel,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                coroutineScope.launch { viewModel.userIntent.send(MainIntent.SelectCat(catModel)) }
            })
            .padding(4.dp)
    ) {
        Text(
            text = catModel.name,
            fontWeight = FontWeight.Bold
        )
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(text = catModel.description, style = MaterialTheme.typography.body2)
        }
    }
}
