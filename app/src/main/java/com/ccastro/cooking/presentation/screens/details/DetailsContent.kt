package com.ccastro.cooking.presentation.screens.details

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.components.especificos.ImagesPresentation
import com.ccastro.cooking.presentation.components.genericos.ClickableCustomColor
import com.ccastro.cooking.presentation.components.genericos.ImageCardResizable
import com.ccastro.cooking.presentation.components.genericos.InformativeText
import com.ccastro.cooking.presentation.components.especificos.IngredienteItem
import com.ccastro.cooking.presentation.components.especificos.LocationInformation
import com.ccastro.cooking.presentation.components.genericos.ParagraphText
import com.ccastro.cooking.presentation.components.especificos.PreparacionItem
import com.ccastro.cooking.presentation.components.genericos.IconBackArrow
import com.ccastro.cooking.presentation.components.genericos.IconImageClicked
import com.ccastro.cooking.presentation.components.genericos.TittleSecondaryBoldText
import com.ccastro.cooking.presentation.components.genericos.TittleText
import com.ccastro.cooking.presentation.navigation.AppScreens
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


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        IconBackArrow(modifier = Modifier.fillMaxWidth()) {
            navHost.navigate(AppScreens.Home.route)
        }

        ImagesPresentation(viewModel.receta.imagenes, imagesSize = 350)

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            InformacionDeReceta(receta = viewModel.receta, navHost = navHost)
            IngredientesComponent(viewModel.receta.ingredientes)
            Preparacion(preparacion = viewModel.receta.preparacion)

        }

    }
    
}


@Composable
fun InformacionDeReceta(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController) {

    Column(modifier) {
        TittleText(text = receta.nombre)
        Divider(thickness = 3.dp, modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
        ClickableCustomColor {
            ParagraphText(text = receta.descripcion)
        }
        LocationInformation(
            receta = receta, Arrangement.Start,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 12.dp),
            navHost = navHost
        )
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
                .padding(top = 24.dp, bottom = 16.dp),
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
    val procedimiento = preparacion.split("\n")

    Column(modifier) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TittleSecondaryBoldText(text = stringResource(R.string.preparaci_n))
            InformativeText(text = "${pagerState.currentPage+1} / ${procedimiento.size}")
        }
        HorizontalPager(
            modifier =  Modifier.padding(bottom = 40.dp, top = 16.dp),
            count = procedimiento.size,
            state = pagerState,
        )
        { page ->
            PreparacionItem(procedimiento[page], Modifier.padding(vertical = 16.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp))
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


