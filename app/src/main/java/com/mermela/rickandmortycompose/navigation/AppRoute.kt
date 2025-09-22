package com.mermela.rickandmortycompose.navigation

sealed interface AppRoute {
    data object Home : AppRoute
    data object Details : AppRoute
}