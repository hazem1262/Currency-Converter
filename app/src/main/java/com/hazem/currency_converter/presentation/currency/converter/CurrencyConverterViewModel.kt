package com.hazem.currency_converter.presentation.currency.converter

import com.hazem.currency_converter.base.BaseViewModel
import com.hazem.currency_converter.data.remote.currency.CurrencyRepositoryContract
import com.hazem.currency_converter.presentation.currency.converter.mapper.CurrencyUiMapper
import com.hazem.currency_converter.presentation.currency.converter.mvi.CurrencyConverterState
import com.hazem.currency_converter.presentation.currency.history.model.TransactionHistoryArgs
import com.hazem.currency_converter.utils.network.ApplicationException
import com.hazem.currency_converter.utils.network.noInternetConnectionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepositoryContract
): BaseViewModel() {
    val uiState = MutableStateFlow(CurrencyConverterState())

    fun getAvailableCurrencies() {
        wrapBlockingOperation {
            val availableCurrencyResponse = currencyRepository.getAvailableCurrencies()
            val availableCurrencies = CurrencyUiMapper.toCurrencyUiModel(availableCurrencyResponse)
            uiState.value = uiState.value.copy(
                currencies = availableCurrencies,
                selectedFromCurrency = availableCurrencies.first(),
                selectedToCurrency = availableCurrencies.first(),
                exception = null
            )
        }
    }

    private fun convertCurrency() {
        val currentState = uiState.value
        if (currentState.fromTextFieldString.toDoubleOrNull() == null) return
        wrapBlockingOperation {
            val convertResponse = currencyRepository.covertCurrency(
                from = currentState.selectedFromCurrency?.acronym?:"",
                to = currentState.selectedToCurrency?.acronym?:"",
                amount = currentState.fromTextFieldString.toDouble()
            )
            uiState.value = uiState.value.copy(toTextFieldString = convertResponse.result?:"", exception = null)
        }
    }

    private fun convertCurrencyRevered() {
        val currentState = uiState.value
        if (currentState.toTextFieldString.toDoubleOrNull() == null) return
        wrapBlockingOperation {
            val convertResponse = currencyRepository.covertCurrency(
                from = currentState.selectedToCurrency?.acronym?:"",
                to = currentState.selectedFromCurrency?.acronym?:"",
                amount = currentState.toTextFieldString.toDouble()
            )
            uiState.value = uiState.value.copy(fromTextFieldString = convertResponse.result?:"", exception = null)
        }
    }

    private fun checkIfConversionNeeded() {
        val currentState = uiState.value
        if(currentState.fromTextFieldString.isNotEmpty()) {
            convertCurrency()
        } else if(currentState.toTextFieldString.isNotEmpty()) {
            convertCurrencyRevered()
        }
    }

    fun onNewFromCurrencySelected(index: Int) {
        val newSelectedFromCurrency = uiState.value.currencies?.get(index)
        uiState.value = uiState.value.copy(selectedFromCurrency = newSelectedFromCurrency)
        checkIfConversionNeeded()
    }

    fun onNewToCurrencySelected(index: Int) {
        val newSelectedToCurrency = uiState.value.currencies?.get(index)
        uiState.value = uiState.value.copy(selectedToCurrency = newSelectedToCurrency)
        checkIfConversionNeeded()
    }

    fun onValueFromChanged(newValue: String) {
        uiState.value = uiState.value.copy(fromTextFieldString = newValue)
        if(newValue.isNotEmpty()) convertCurrency()
    }

    fun onValueToChanged(newValue: String) {
        uiState.value = uiState.value.copy(toTextFieldString = newValue)
        if (newValue.isNotEmpty()) convertCurrencyRevered()
    }

    fun swapValues() {
        val currentState = uiState.value
        val tempCurrency = currentState.selectedFromCurrency
        uiState.value = uiState.value.copy(selectedFromCurrency = currentState.selectedToCurrency, selectedToCurrency = tempCurrency)
        convertCurrency()
    }

    fun getTransactionHistoryArgs(): TransactionHistoryArgs {
        val currentState = uiState.value
        return TransactionHistoryArgs(from = currentState.selectedFromCurrency?.acronym?:"", to = currentState.selectedToCurrency?.acronym?:"")
    }

    override fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            uiState.value = uiState.value.copy(exception = throwable)
        } else {
            uiState.value = uiState.value.copy(exception = noInternetConnectionException)
        }
    }
}