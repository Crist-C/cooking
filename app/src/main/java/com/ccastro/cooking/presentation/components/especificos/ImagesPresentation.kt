package com.ccastro.cooking.presentation.components.especificos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.cooking.presentation.components.genericos.ImageCardResizable
import com.ccastro.cooking.presentation.ui.theme.Blue80
import com.ccastro.cooking.presentation.ui.theme.BlueVariant40
import com.ccastro.cooking.presentation.ui.theme.CookingTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagesPresentation(imagesList: List<String>, modifier: Modifier = Modifier, imagesSize: Int) {

    val pageState = rememberPagerState(initialPage = 1)

    Column(modifier = modifier.fillMaxSize(),verticalArrangement = Arrangement.Top)
    {

        HorizontalPager(
            count = imagesList.size, state = pageState,
            contentPadding = PaddingValues(horizontal = 80.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        { page ->
            ImageCardResizable(
                urlImage = imagesList[page],
                calculateCurrentOffsetForPage(page).absoluteValue,
                modifier = modifier, size = imagesSize)
        }
        StateDots(imagesList.size, pageState, Modifier.padding(top = 4.dp))

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StateDots(count: Int, pageState: PagerState, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val isThisSelected = remember {
        mutableIntStateOf(0)
    }
    isThisSelected.intValue = pageState.currentPage

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) {
            Divider(
                thickness = 8.dp,
                color = if (isThisSelected.intValue == it ) BlueVariant40 else Blue80,
                modifier = Modifier
                    .padding(4.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .clickable {
                        isThisSelected.intValue = it
                        scope.launch {
                            pageState.animateScrollToPage(it)
                        }
                    }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImageCarouselContentPreview() {
    CookingTheme {
        ImagesPresentation(imagesList = listOf(
            "https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp",
            "https://cdn7.recetasdeescandalo.com/wp-content/uploads/2020/06/Aji-de-gallina.-" +
                    "Receta-peruana-tradicional-y-deliciosa.jpg.webp",
            "https://www.megamaxi.com/wp-content/uploads/2023/03/Aji-de-gallina.jpg"),
            imagesSize = 250
        )
    }
}