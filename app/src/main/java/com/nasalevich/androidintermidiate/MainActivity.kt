package com.nasalevich.androidintermidiate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.setContent
import com.nasalevich.androidintermidiate.mainPage.presentation.MainViewModel
import com.nasalevich.androidintermidiate.mainPage.presentation.ui.MainPage
import com.nasalevich.androidintermidiate.resources.AndroidIntermidiateTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidIntermidiateTheme {
                MainPage(
                    onBackPressedDispatcher = onBackPressedDispatcher,
                    viewModel = viewModel
                )
            }
        }
    }
}