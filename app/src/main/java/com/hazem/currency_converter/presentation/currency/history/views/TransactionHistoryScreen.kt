package com.hazem.currency_converter.presentation.currency.history.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.presentation.currency.history.model.CurrencyRate
import com.hazem.currency_converter.utils.components.LoadingIndicator
import com.jaikeerthick.composable_graphs.color.*
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TransactionHistoryScreen(
    isLoading:Boolean,
    historicalList: List<CurrencyRate>,
    otherCurrenciesList: List<CurrencyRate>
) {
    if (isLoading) {
        LoadingIndicator()
        return
    }
    Column {
        Box(modifier = Modifier
            .height(200.dp)
        ) {
            LineGraph(
                xAxisData = historicalList.map { GraphData.String(it.date) },
                yAxisData = historicalList.map { it.rate.toDouble() },
                style = LineGraphStyle(
                    visibility = LinearGraphVisibility(
                        isHeaderVisible = true,
                        isYAxisLabelVisible = true,
                        isCrossHairVisible = true
                    ),
                    colors = LinearGraphColors(
                        lineColor = GraphAccent2,
                        pointColor = GraphAccent2,
                        clickHighlightColor = PointHighlight2,
                        fillGradient = Brush.verticalGradient(
                            listOf(Gradient3, Gradient2)
                        )
                    )
                )
            )
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
}