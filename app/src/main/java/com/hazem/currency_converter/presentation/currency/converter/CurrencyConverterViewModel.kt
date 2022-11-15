package com.hazem.currency_converter.presentation.currency.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.currency_converter.data.remote.currency.CurrencyRepositoryContract
import com.hazem.currency_converter.presentation.currency.converter.mapper.CurrencyUiMapper
import com.hazem.currency_converter.presentation.currency.converter.mvi.CurrencyConverterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepositoryContract
): ViewModel() {
    val uiState = MutableStateFlow(CurrencyConverterState())

    fun getAvailableCurrencies() {
        viewModelScope.launch {
            val availableCurrencyResponse = currencyRepository.getAvailableCurrencies()
            val availableCurrencies = CurrencyUiMapper.toCurrencyUiModel(availableCurrencyResponse)
            uiState.value = uiState.value.copy(currencies = availableCurrencies, selectedFromCurrency = availableCurrencies.first(), selectedToCurrency = availableCurrencies.first())
        }
    }

    private fun convertCurrency() {
        val currentState = uiState.value
        if (currentState.fromTextFieldString.toDoubleOrNull() == null) return
        viewModelScope.launch {
            val convertResponse = currencyRepository.covertCurrency(
                from = currentState.selectedFromCurrency?.acronym?:"",
                to = currentState.selectedToCurrency?.acronym?:"",
                amount = currentState.fromTextFieldString.toDouble()
            )
            uiState.value = uiState.value.copy(toTextFieldString = convertResponse.result?:"")
        }
    }

    private fun convertCurrencyRevered() {
        val currentState = uiState.value
        if (currentState.toTextFieldString.toDoubleOrNull() == null) return
        viewModelScope.launch {
            val convertResponse = currencyRepository.covertCurrency(
                from = currentState.selectedToCurrency?.acronym?:"",
                to = currentState.selectedFromCurrency?.acronym?:"",
                amount = currentState.toTextFieldString.toDouble()
            )
            uiState.value = uiState.value.copy(fromTextFieldString = convertResponse.result?:"")
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
}