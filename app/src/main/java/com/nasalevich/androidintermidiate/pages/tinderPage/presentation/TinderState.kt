package com.nasalevich.androidintermidiate.pages.tinderPage.presentation

import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel

sealed class TinderState {

    object Idle : TinderState()

    object Loading : TinderState()

    object Error : TinderState()

    data class Success(val characters: List<CharacterModel>) : TinderState()
}
