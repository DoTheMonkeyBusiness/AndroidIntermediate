package com.nasalevich.androidintermidiate.navigation

sealed class Screen(val route: String) {

    object Main : Screen("MainScreen") {
        const val ARG_NAME: String = "cat"
    }

    object Edit : Screen("EditScreen") {
        const val ARG_ID: String = "id"
        const val ARG_NAME: String = "name"
        const val ARG_DESCRIPTION: String = "description"
    }
}
