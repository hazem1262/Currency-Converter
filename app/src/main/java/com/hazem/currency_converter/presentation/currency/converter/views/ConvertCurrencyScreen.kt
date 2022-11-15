package com.hazem.currency_converter.presentation.currency.converter.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Transform
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.R
import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel

@Composable
fun ConvertCurrencyScreen(
    availableCurrencies:List<CurrencyUiModel>,
    onFromSelected: (index:Int) -> Unit,
    onToSelected: (index:Int) -> Unit,
    fromString:String,
    onValueFromChanged:(String) -> Unit,
    toString:String,
    onValueToChanged:(String) -> Unit,
    onDetailsClicked:() -> Unit,
    initialSelectedFromIndex:Int,
    initialSelectedToIndex:Int
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            CustomDropDownBtn(
                modifier = Modifier.weight(1f),
                currencies = availableCurrencies,
                onSelect = onFromSelected,
                selectedIndex = initialSelectedFromIndex
            )
            Icon(Icons.Filled.Transform, "Convert", modifier = Modifier.padding(horizontal = 8.dp))
            CustomDropDownBtn(
                modifier = Modifier.weight(1f),
                currencies = availableCurrencies,
                onSelect = onToSelected,
                selectedIndex = initialSelectedToIndex
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            CustomTextField(
                modifier = Modifier.weight(1f),
                initialValue = fromString,
                onValueChanged = onValueFromChanged,
            )
            Spacer(modifier = Modifier.width(32.dp))
            CustomTextField(
                modifier = Modifier.weight(1f),
                initialValue = toString,
                onValueChanged = onValueToChanged,
            )
        }

        Button(onClick = onDetailsClicked) {
            Text(text = stringResource(id = R.string.details))
        }
    }
}