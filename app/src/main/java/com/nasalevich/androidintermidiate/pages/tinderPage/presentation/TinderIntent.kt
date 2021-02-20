package com.nasalevich.androidintermidiate.pages.tinderPage.presentation

sealed class TinderIntent {

    object FetchData : TinderIntent()

    object Like : TinderIntent()

    object Dislike : TinderIntent()
}
