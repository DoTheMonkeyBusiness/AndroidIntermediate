package com.nasalevich.androidintermidiate.mainPage.presentation

import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel

sealed class MainIntent {

    object FetchCats : MainIntent()

    object PressBack : MainIntent()

    data class SelectCat(val catModel: CatModel) : MainIntent()

    data class EditCat(val catModel: CatModel) : MainIntent()
}
