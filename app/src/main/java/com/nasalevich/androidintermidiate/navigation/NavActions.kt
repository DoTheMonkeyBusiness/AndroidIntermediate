package com.nasalevich.androidintermidiate.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

class NavActions(navController: NavHostController) {
    val mainPage: () -> Unit = {
        navController.navigate(
            Screen.Main.route
        )
    }

    val tinderPage: () -> Unit = {
        navController.navigate(
            Screen.Tinder.route
        )
    }
}