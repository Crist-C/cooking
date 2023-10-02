package com.ccastro.cooking.presentation.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.R
import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.components.genericos.ClickableCustomColor
import com.ccastro.cooking.presentation.components.genericos.ImageCardResizable
import com.ccastro.cooking.presentation.components.genericos.InformativeText
import com.ccastro.cooking.presentation.components.especificos.IngredienteItem
import com.ccastro.cooking.presentation.components.especificos.LocationInformation
import com.ccastro.cooking.presentation.components.genericos.ParagraphText
import com.ccastro.cooking.presentation.components.especificos.PreparacionItem
import com.ccastro.cooking.presentation.components.genericos.TittleSecondaryBoldText
import com.ccastro.cooking.presentation.components.genericos.TittleText
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


@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    viewModel: DetalisViewModel = hiltViewModel(),
    navHost: NavHostController
) {

    val state = viewModel.state


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, top = 80.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        ImagesPresentation(state.receta.imagenes, imagesSize = 250)
        InformacionDeReceta(receta = state.receta, navHost = navHost)
        IngredientesComponent(state.receta.ingredientes)
        Preparacion(preparacion = state.receta.preparacion)

    }
    
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagesPresentation(imagesList: List<String>, modifier: Modifier = Modifier, imagesSize: Int) {

    val pageState = rememberPagerState(initialPage = 1)

    Column(modifier = modifier.fillMaxSize(),verticalArrangement = Arrangement.Top)
    {

        HorizontalPager(
            count = imagesList.size, state = pageState,
            contentPadding = PaddingValues(horizontal = 120.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
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

@Composable
fun InformacionDeReceta(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController) {

    Column(modifier) {
        TittleText(text = receta.nombre)
        Divider(thickness = 3.dp, modifier = Modifier.padding(1.dp))
        ClickableCustomColor {
            ParagraphText(text = receta.descripcion)
        }
        ClickableCustomColor {
            LocationInformation(
                location = receta.location, Arrangement.Start,
                modifier = Modifier.wrapContentSize().padding(top = 8.dp),
                navHost = navHost
            )

        }
    }

}

@Composable
fun IngredientesComponent(listaIngredientes: List<Ingredientes>, modifier: Modifier = Modifier) {

    var ingredientesChuleados by remember {
        mutableIntStateOf(0)
    }

    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TittleSecondaryBoldText(text = stringResource(R.string.ingredientes))
            InformativeText(text = "$ingredientesChuleados / ${listaIngredientes.size}")
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(300.dp)
                .verticalScroll(rememberScrollState())
        ) {
            listaIngredientes.map {
                IngredienteItem(
                    ingrediente = it,
                    chekedLambda = { ingredientesChuleados++ },
                    unChekedLambda = { ingredientesChuleados-- }
                )
                Divider(thickness = 1.dp, modifier = Modifier.padding(1.dp))
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Preparacion(preparacion: String, modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState(initialPage = 0)
    val procedimiento by remember {
        mutableStateOf(preparacion.split("\\n"))
    }

    Column(modifier) {
        TittleSecondaryBoldText(text = stringResource(R.string.preparaci_n))

        HorizontalPager(
            modifier =  Modifier.padding(bottom = 24.dp),
            count = procedimiento.size,
            state = pagerState,
        )
        { page ->
            PreparacionItem(procedimiento[page])
        }

    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailsContentPreview() {
    CookingTheme {
        DetailsContent(navHost = rememberNavController())
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImageCarouselContentPreview() {
    CookingTheme {
        ImagesPresentation(imagesList = listOf(
            "https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp",
            "https://cdn7.recetasdeescandalo.com/wp-content/uploads/2020/06/Aji-de-gallina.-Receta-peruana-tradicional-y-deliciosa.jpg.webp",
            "https://www.megamaxi.com/wp-content/uploads/2023/03/Aji-de-gallina.jpg"),
            imagesSize = 250
        )
    }
}
