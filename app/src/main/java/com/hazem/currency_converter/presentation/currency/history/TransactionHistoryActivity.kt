package com.hazem.currency_converter.presentation.currency.history

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.presentation.currency.history.model.TransactionHistoryArgs
import com.hazem.currency_converter.presentation.currency.history.views.TransactionHistoryScreen
import com.hazem.currency_converter.ui.theme.CurrencyConverterTheme
import com.hazem.currency_converter.utils.components.ErrorComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionHistoryActivity : ComponentActivity() {
    private val viewModel: TransactionHistoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: TransactionHistoryArgs? = if (Build.VERSION.SDK_INT >= 33) {
            intent.extras?.getParcelable(TRANSACTION_HISTORY_EXTRA_KEY, TransactionHistoryArgs::class.java)
        } else {
            intent.getParcelableExtra(TRANSACTION_HISTORY_EXTRA_KEY)
        }
        args?.let {
            viewModel.getHistoricalData(it)
        }
        setContent {
            val state = viewModel.uiState.collectAsState().value
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    color = MaterialTheme.colors.background
                ) {
                    state.exception?.let {
                        ErrorComposable(
                            applicationException = it,
                            onRetry = {
                                viewModel.getHistoricalData(args!!)
                            }
                        )
                        return@Surface
                    }
                    TransactionHistoryScreen(
                        isLoading = state.isLoading,
                        historicalList = state.historicalList?: arrayListOf(),
                        otherCurrenciesList = state.otherCurrenciesList?: arrayListOf()
                    )
                }
            }
        }
    }
    companion object {
        const val TRANSACTION_HISTORY_EXTRA_KEY = "TRANSACTION_HISTORY_EXTRA_KEY"
    }
}