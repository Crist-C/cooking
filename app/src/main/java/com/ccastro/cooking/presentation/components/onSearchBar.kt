package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.cooking.R
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { stringResource(R.string.que_deseas_buscar) },
                modifier = Modifier.focusable(true).background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                maxLines = 1,
                leadingIcon = { Icon(Default.Search, contentDescription = "") }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    onSearch(searchQuery)
                }
            ) {
                Text(text = stringResource(R.string.buscar))
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    CookingTheme {
        SearchBar(onSearch = { it-> })
    }
}