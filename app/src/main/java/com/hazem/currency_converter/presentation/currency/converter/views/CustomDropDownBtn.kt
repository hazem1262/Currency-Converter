package com.hazem.currency_converter.presentation.currency.converter.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel

@Composable
fun CustomDropDownBtn(
    modifier:Modifier = Modifier,
    currencies:List<CurrencyUiModel>,
    selectedIndex: Int,
    onSelect: (index:Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier.border(1.dp, Color.Gray)) {
        Text(
            currencies[selectedIndex].acronym,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            currencies.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onSelect.invoke(index)
                }) {
                    Text(text = s.name)
                }
            }
        }
    }
}