package com.hazem.currency_converter.presentation.currency.converter

import androidx.lifecycle.ViewModel
import com.hazem.currency_converter.data.remote.currency.CurrencyRepositoryContract
import com.hazem.currency_converter.presentation.currency.converter.mvi.CurrencyConverterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepositoryContract
): ViewModel() {
    fun getAvailableCurrencies() {
        TODO("Not yet implemented")
    }

    fun onFromSelected(index: Int) {
        TODO("Not yet implemented")
    }

    fun onToSelected(index: Int) {
        TODO("Not yet implemented")
    }

    fun onValueFromChanged(it: String) {
        TODO("Not yet implemented")
    }

    fun onValueToChanged(it: String) {
        TODO("Not yet implemented")
    }

    val uiState = MutableStateFlow(CurrencyConverterState())
}