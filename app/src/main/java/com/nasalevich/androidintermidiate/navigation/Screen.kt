package com.nasalevich.androidintermidiate.navigation

sealed class Screen(val route: String) {

    object Main : Screen("MainScreen")

    object Tinder : Screen("TinderScreen")

    object Home : Screen("HomeScreen")
}
