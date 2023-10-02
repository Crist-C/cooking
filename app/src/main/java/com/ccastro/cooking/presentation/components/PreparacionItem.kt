package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ccastro.cooking.presentation.ui.theme.Blue40

@Composable
fun PreparacionItem(procedimiento: String) {
    Surface(
        Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp), shape = CircleShape,
        shadowElevation = 4.dp, color = Blue40
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .padding(30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            ContentText(procedimiento)
        }
    }
}