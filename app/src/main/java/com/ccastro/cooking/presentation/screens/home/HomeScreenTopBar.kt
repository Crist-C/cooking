package com.ccastro.cooking.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.R
import com.ccastro.cooking.presentation.components.especificos.RecetaItem
import com.ccastro.cooking.presentation.components.genericos.ClickableCustomColor
import com.ccastro.cooking.presentation.components.genericos.InformativeText
import com.ccastro.cooking.presentation.ui.theme.Blue200
import com.ccastro.cooking.presentation.ui.theme.Blue80
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun HomeScreenTopBar(navHost: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val searchText by viewModel.searchText.collectAsState()
    val recetas by viewModel.recetas.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 32.dp)
                .background(Color.Transparent),
                //.border(1.dp, Color.Black, CircleShape),
            placeholder = {
                Text(text = "Search")
          },
            leadingIcon = {
                Image(imageVector = Icons.Outlined.Search, contentDescription = "",
                    colorFilter = ColorFilter.tint(Blue200))
            },
            trailingIcon = {
                ClickableCustomColor {
                    Image(imageVector = Icons.Outlined.Clear, contentDescription = "",
                        colorFilter = ColorFilter.tint(Blue200), modifier = Modifier.clickable {
                            viewModel.onSearchTextChange("")
                        })
                }
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        if(isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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
                    items(recetas,) { receta ->
                        RecetaItem(receta = receta, navHost = navHost)
                    }
                }

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