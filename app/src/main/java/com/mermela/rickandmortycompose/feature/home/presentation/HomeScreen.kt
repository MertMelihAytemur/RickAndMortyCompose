package com.mermela.rickandmortycompose.feature.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mermela.rickandmortycompose.core.network.ApiState
import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse
import com.mermela.rickandmortycompose.feature.home.data.dto.response.Result
import com.mermela.rickandmortycompose.feature.home.presentation.components.CharactersCarousel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: ApiState<GetCharactersByPageResponse>
) {
    val characters = when (state) {
        is ApiState.Success -> state.response.results?.filterNotNull() ?: emptyList()
        else -> emptyList()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (characters.isNotEmpty()) {
            CharactersCarousel(
                characters = characters,
                onPageChanged = {

                },
                navigateToDetail = {

                }
            )
        } else {
            Text(text = "No characters loaded")
        }
    }
}


