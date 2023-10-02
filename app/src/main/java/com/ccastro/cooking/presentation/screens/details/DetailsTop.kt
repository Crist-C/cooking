package com.ccastro.cooking.presentation.screens.details

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.presentation.components.IconImageClicked
import com.ccastro.cooking.presentation.navigation.AppScreens.home
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTop(navHost: NavHostController) {
    TopAppBar(
        modifier = Modifier .clip(RoundedCornerShape(20.dp)).padding(0.dp),
        title = {

        },
        navigationIcon ={
            IconImageClicked(Icons.Outlined.ArrowBack, Modifier.size(50.dp).clip(CircleShape)){
                navHost.navigate(home.route)
            }
        }

        )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DetailsTopPreview(){
    CookingTheme {
        DetailsTop(navHost = rememberNavController())
    }
}