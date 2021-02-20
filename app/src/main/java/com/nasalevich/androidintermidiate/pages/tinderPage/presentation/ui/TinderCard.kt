package com.nasalevich.androidintermidiate.pages.tinderPage.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalComposeUiApi
@Composable
fun TinderCard(
    characterModel: CharacterModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .shadow(2.dp, RoundedCornerShape(16.dp)),
    ) {
        CardContent(characterModel = characterModel)
    }
}

@ExperimentalComposeUiApi
@Composable
fun CardContent(
    characterModel: CharacterModel,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (descriptionRef) = createRefs()

        CoilImage(
            data = characterModel.image,
            contentDescription = "description",
            loading = {
                Box {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            error = {
                Text(text = "Error")
            },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
        )
        ContentDescription(
            characterModel = characterModel,
            modifier = Modifier
                .constrainAs(descriptionRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Composable
fun ContentDescription(
    characterModel: CharacterModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight(0.15f)
            .fillMaxWidth(0.4f)
            .clip(RoundedCornerShape(topRight = 16.dp))
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = characterModel.name,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = characterModel.description,
        )
    }
}