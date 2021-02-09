package com.nasalevich.androidintermidiate.pages.mainPage.presentation

import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel

sealed class MainState {

    object Idle : MainState()

    object Loading : MainState()

    data class CatSelected(val cat: CatModel) : MainState()

    data class Success(val cats: List<CatModel>) : MainState()
}
