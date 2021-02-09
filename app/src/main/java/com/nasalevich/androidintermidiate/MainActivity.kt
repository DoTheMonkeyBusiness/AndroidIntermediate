package com.nasalevich.androidintermidiate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.nasalevich.androidintermidiate.navigation.NavGraph
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.ui.MainPage
import com.nasalevich.androidintermidiate.resources.AndroidIntermidiateTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidIntermidiateTheme {
                NavGraph(onBackPressedDispatcher)
            }
        }
    }
}