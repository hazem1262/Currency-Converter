package com.hazem.currency_converter.presentation.currency.converter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.hazem.currency_converter.presentation.currency.converter.views.ConvertCurrencyScreen
import com.hazem.currency_converter.presentation.currency.history.TransactionHistoryActivity
import com.hazem.currency_converter.ui.theme.CurrencyConverterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterActivity : ComponentActivity() {
    private val viewModel: CurrencyConverterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAvailableCurrencies()
        setContent {
            val state = viewModel.uiState.collectAsState().value
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConvertCurrencyScreen(
                        availableCurrencies = state.currencies?: arrayListOf(),
                        onFromSelected = { index ->
                            viewModel.onFromSelected(index)
                        },
                        onToSelected = {index ->
                            viewModel.onToSelected(index)
                        },
                        fromString = state.fromTextFieldString,
                        onValueFromChanged = {
                            viewModel.onValueFromChanged(it)
                        },
                        toString = state.toTextFieldString,
                        onValueToChanged = {
                            viewModel.onValueToChanged(it)
                        },
                        onDetailsClicked = {
                            val intent = Intent(this, TransactionHistoryActivity::class.java)
                            intent.putExtra(TransactionHistoryActivity.TRANSACTION_HISTORY_EXTRA_KEY, "")
                            startActivity(intent)
                        },
                        initialSelectedToIndex = state.selectedIndexToCurrency,
                        initialSelectedFromIndex = state.selectedIndexFromCurrency
                    )
                }
            }
        }
    }
}