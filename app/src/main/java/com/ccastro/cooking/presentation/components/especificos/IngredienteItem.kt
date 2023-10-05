package com.ccastro.cooking.presentation.components.especificos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ccastro.cooking.domain.models.Ingrediente
import com.ccastro.cooking.presentation.components.genericos.ParagraphText
import com.ccastro.cooking.presentation.ui.theme.Green40

@Composable
fun IngredienteItem(
    ingrediente: Ingrediente, modifier: Modifier = Modifier,
    chekedLambda: () -> Unit = {}, unChekedLambda: () -> Unit = {}
) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier
            .wrapContentSize()
            .background(if (isChecked) Green40 else Color.Transparent, shape = CircleShape)
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                isChecked = !isChecked
                if (isChecked) chekedLambda.invoke() else unChekedLambda.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier.padding(0.dp),
            checked = isChecked,
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.onPrimaryContainer),
            onCheckedChange = {
                isChecked = !isChecked
                if(isChecked) chekedLambda.invoke() else unChekedLambda.invoke()
            }
        )
        ParagraphText(text = "${ingrediente.nombre} ${ingrediente.cantidad}")
    }
}
