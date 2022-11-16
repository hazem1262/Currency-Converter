package com.hazem.currency_converter.presentation.currency.history.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.presentation.currency.history.model.CurrencyRate
import com.hazem.currency_converter.utils.components.LoadingIndicator

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TransactionHistoryScreen(
    isLoading:Boolean,
    historicalList: List<CurrencyRate>,
    otherCurrenciesList: List<CurrencyRate>,
) {
    if (isLoading) {
        LoadingIndicator()
        return
    }
    Row {
        Column(modifier = Modifier.weight(1f)) {
            historicalList.forEach {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = it.date,
                        style = TextStyle(
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "${it.currencyFrom} -> ${it.currencyTo}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it.rate)
                }
            }
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            otherCurrenciesList.forEach {
                item {
                    Text(text = "${it.currencyTo} : ${it.rate}")
                }
            }
        }
    }
}