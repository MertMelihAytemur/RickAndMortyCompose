package com.mermela.rickandmortycompose.feature.home.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mermela.rickandmortycompose.feature.home.data.dto.response.Result

@Composable
fun CharactersCarousel(
    characters: List<Result>,
    onPageChanged: (Int) -> Unit = {},
    navigateToDetail: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier
    ) {
        RecommendedDramasCarousel(
            characters = characters,
            onPageChanged = onPageChanged,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun RecommendedDramasCarousel(
    characters: List<Result>,
    onPageChanged: (Int) -> Unit = {},
    navigateToDetail: (Int) -> Unit = {}
) {
    CommonCarousel(
        items = characters,
        itemWidth = 300.dp,
        itemHeight = 300.dp,
        onPageChanged = { onPageChanged(it) },
        infinite = true
    ) { character ->
        character.image?.let { poster ->
            androidx.compose.material3.Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                AsyncImage(
                    model = poster,
                    contentDescription = character.name,
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun <T> CommonCarousel(
    items: List<T>,
    modifier: Modifier = Modifier,
    itemWidth: Dp = 260.dp,
    itemHeight: Dp = 356.dp,
    itemSpacing: Dp = 8.dp,
    infinite: Boolean = false,
    onPageChanged: ((Int) -> Unit)? = null,
    itemContent: @Composable (item: T) -> Unit
) {
    if (items.isEmpty()) return

    val actualPageCount = if (infinite) Int.MAX_VALUE else items.size
    val startIndex = if (infinite) Int.MAX_VALUE / 2 else 0
    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { actualPageCount }
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val horizontalPadding = (screenWidth - itemWidth) / 2
    val actualIndex = if (infinite)
        pagerState.currentPage % items.size
    else
        pagerState.currentPage

    LaunchedEffect(actualIndex) {
        onPageChanged?.invoke(actualIndex)
    }

    HorizontalPager(
        state = pagerState,
        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
    ) { page ->
        val item = items[page % items.size]
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val scale = 1f - (0.2f * kotlin.math.abs(pageOffset))
        val alpha = 1f - (0.3f * kotlin.math.abs(pageOffset))

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale.coerceIn(0.85f, 1f)
                    scaleY = scale.coerceIn(0.85f, 1f)
                    this.alpha = alpha.coerceIn(0.7f, 1f)
                }
                .width(itemWidth)
                .height(itemHeight)
        ) {
            itemContent(item)
        }
    }
}