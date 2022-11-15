package com.hazem.currency_converter.presentation.currency.converter.mvi

import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel
import com.hazem.currency_converter.utils.network.ApplicationException

data class CurrencyConverterState(
    val currencies:List<CurrencyUiModel>? = null,
    var selectedFromCurrency:CurrencyUiModel? = null,
    var selectedToCurrency:CurrencyUiModel? = null,
    var convertedToCurrency:String = "",
    var convertedFromCurrency:String = "",
    var fromTextFieldString:String = "1",
    var toTextFieldString:String = "1",
    var exception:ApplicationException? = null
) {
    val isLoading get() = exception == null && currencies == null
}