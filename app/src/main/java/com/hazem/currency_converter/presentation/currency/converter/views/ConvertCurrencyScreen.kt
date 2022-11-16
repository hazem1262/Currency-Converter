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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.R
import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel
import com.hazem.currency_converter.utils.components.LoadingIndicator

@Composable
fun ConvertCurrencyScreen(
    isLoading:Boolean,
    availableCurrencies:List<CurrencyUiModel>,
    onFromSelected: (index:Int) -> Unit,
    onToSelected: (index:Int) -> Unit,
    fromString:String,
    onValueFromChanged:(String) -> Unit,
    toString:String,
    onValueToChanged:(String) -> Unit,
    onDetailsClicked:() -> Unit,
    onSwapClicked:() -> Unit,
    initialSelectedFromIndex:Int,
    initialSelectedToIndex:Int
) {
    if (isLoading) {
        LoadingIndicator()
        return
    }
    Column(modifier = Modifier.padding(16.dp).testTag(CONVERT_CURRENCY_SCREEN_TAG), horizontalAlignment = Alignment.CenterHorizontally) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            CustomDropDownBtn(
                modifier = Modifier.weight(1f),
                currencies = availableCurrencies,
                onSelect = onFromSelected,
                selectedIndex = initialSelectedFromIndex
            )
            Button(modifier = Modifier.padding(horizontal = 8.dp), onClick = { onSwapClicked.invoke() }) {
                Icon(
                    Icons.Filled.Transform,
                    stringResource(id = R.string.convert),
                )
            }
            CustomDropDownBtn(
                modifier = Modifier.weight(1f),
                currencies = availableCurrencies,
                onSelect = onToSelected,
                selectedIndex = initialSelectedToIndex
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = onDetailsClicked) {
            Text(text = stringResource(id = R.string.details))
        }
    }
}

const val CONVERT_CURRENCY_SCREEN_TAG = "CONVERT_CURRENCY_SCREEN_TAG"