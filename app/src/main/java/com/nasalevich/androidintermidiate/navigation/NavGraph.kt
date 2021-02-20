package com.nasalevich.androidintermidiate.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nasalevich.androidintermidiate.pages.homePage.presentation.ui.HomePage
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.ui.MainPage
import com.nasalevich.androidintermidiate.pages.tinderPage.presentation.ui.TinderPage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun NavGraph(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    startDestination: String = Screen.Home.route,
) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomePage(actions)
        }
        composable(Screen.Main.route) {
            MainPage(
                onBackPressedDispatcher = onBackPressedDispatcher,
            )
        }
        composable(Screen.Tinder.route) {
            TinderPage()
        }
    }
}