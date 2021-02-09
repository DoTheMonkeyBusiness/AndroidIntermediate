package com.nasalevich.androidintermidiate.pages.homePage.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nasalevich.androidintermidiate.navigation.NavActions

@Composable
fun HomePage(
    navActions: NavActions
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ChooseTaskButton(
                text = "Main page",
                onClick = navActions.mainPage
            )
        }
        item {
            ChooseTaskButton(
                text = "Tinder page",
                onClick = navActions.tinderPage
            )
        }
    }
}

@Composable
private fun ChooseTaskButton(
    text: String,
    onClick: () -> Unit,
) {
    val padding = 16.dp
    Button(
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.width(128.dp),
            text = text,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
    }
    Spacer(modifier = Modifier.preferredSize(padding))
}