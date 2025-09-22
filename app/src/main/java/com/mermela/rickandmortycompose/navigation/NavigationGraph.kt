package com.mermela.rickandmortycompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.mermela.rickandmortycompose.feature.home.presentation.HomeRoute

@Composable
fun NavigationGraph(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<AppRoute>(AppRoute.Home) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = {key ->
            when(key) {
                is AppRoute.Home -> NavEntry(key) {
                    HomeRoute(modifier = modifier)
                }
                is AppRoute.Details -> NavEntry(key) {
                    // Details Screen Composable
                }
            }
        }
    )
}