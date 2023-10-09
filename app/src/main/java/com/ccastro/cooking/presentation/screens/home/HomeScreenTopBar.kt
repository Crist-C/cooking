package com.ccastro.cooking.presentation.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.R
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.components.especificos.RecetaItem
import com.ccastro.cooking.presentation.components.genericos.ClickableCustomColor
import com.ccastro.cooking.presentation.components.genericos.InformativeText
import com.ccastro.cooking.presentation.ui.theme.Blue200
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun HomeScreenTopBar(navHost: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {

        SearchingField()

        FilterGroup()

        Spacer(modifier = Modifier.height(16.dp))

        if(isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator( modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center))
            }
        } else {
            RecetaList(navHost = navHost)
        }
    }
}

@Composable
fun SearchingField(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val searchText by viewModel.searchText.collectAsState()

    OutlinedTextField(
        value = searchText,
        onValueChange = viewModel::onSearchTextChange,
        maxLines = 1,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 0.dp)
            .background(Color.Transparent),
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Image(imageVector = Icons.Outlined.Search, contentDescription = "",
                colorFilter = ColorFilter.tint(Blue200))
        },
        trailingIcon = {
            ClickableCustomColor (onClick = { viewModel.onSearchTextChange("") } ) {
                Image(imageVector = Icons.Outlined.Clear, contentDescription = "",
                    colorFilter = ColorFilter.tint(Blue200))
            }
        }

    )

}


@Composable
fun FilterGroup(modifier: Modifier = Modifier, viewModel: HomeViewModel= hiltViewModel()) {

    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start
    ) {
        FilterTopChip(text = "Favorito") { viewModel.agregarEliminarFiltro(Receta.Filtros.Favorito) }
        FilterTopChip(text = "Nombre") { viewModel.agregarEliminarFiltro(Receta.Filtros.Nombre) }
        FilterTopChip(text = "Ingrediente") { viewModel.agregarEliminarFiltro(Receta.Filtros.Ingredientes) }
        FilterTopChip(text = "Pais") { viewModel.agregarEliminarFiltro(Receta.Filtros.Pais) }
        FilterTopChip(text = "Region") { viewModel.agregarEliminarFiltro(Receta.Filtros.Region) }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTopChip(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        modifier = modifier.padding(horizontal = 4.dp),
        onClick = {
            selected = !selected
            onClick()
        },
        label = {
            Text(text)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@Composable
fun RecetaList(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel(),
               navHost: NavHostController) {

    val recetas by viewModel.recetas.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )

            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 150.dp)
    ) {

        if(recetas.isEmpty()) {
            item {
                InformativeText(
                    text = stringResource(R.string.no_se_encontr_alg_na_coincidencia),
                    align = TextAlign.Center
                )
            }

        } else if(recetas.size == 1 && recetas[0].nombre.isEmpty()) {
            item{
                CircularProgressIndicator()
            }
        } else {
            items(recetas) { receta ->
                RecetaItem(receta = receta, navHost = navHost)
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenTopBarPreview() {
    CookingTheme {
        HomeScreenTopBar(navHost = rememberNavController())
    }
}