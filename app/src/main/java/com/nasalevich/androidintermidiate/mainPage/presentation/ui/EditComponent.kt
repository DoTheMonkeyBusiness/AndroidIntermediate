package com.nasalevich.androidintermidiate.mainPage.presentation.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.mainPage.presentation.MainIntent
import com.nasalevich.androidintermidiate.mainPage.presentation.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun EditComponent(
    viewModel: MainViewModel,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    coroutineScope: CoroutineScope,
    catModel: CatModel,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressedDispatcher.onBackPressed()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription="")
                    }
                },
                title = {
                    Text(text = catModel.name)
                }
            )
        }
    ) { innerPadding ->
        BodyContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            coroutineScope = coroutineScope,
            catModel = catModel
        )
    }
}

@Composable
private fun BodyContent(
    modifier: Modifier,
    viewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    catModel: CatModel,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        val nameState = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue(catModel.name) }
        val descriptionState = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue(catModel.description) }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameState.value,
            onValueChange = { nameState.value = it }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it }
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                coroutineScope.launch {
                    viewModel.userIntent.send(
                        MainIntent.EditCat(
                            catModel = CatModel(
                                catModel.id,
                                nameState.value.text,
                                descriptionState.value.text
                            )
                        )
                    )
                }
            }
        ) {
            Text(text = "OK")
        }
    }
}
