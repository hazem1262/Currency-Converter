package com.hazem.currency_converter.presentation.currency.converter.mvi

import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel
import com.hazem.currency_converter.utils.network.ApplicationException

data class CurrencyConverterState(
    val currencies:List<CurrencyUiModel>? = null,
    var selectedFrom:CurrencyUiModel? = null,
    var selectedTo:CurrencyUiModel? = null,
    var convertedToCurrency:String = "",
    var convertedFromCurrency:String = "",
    var fromTextFieldString:String = "",
    var toTextFieldString:String = "",
    var selectedIndexToCurrency:Int = 0,
    var selectedIndexFromCurrency:Int = 0,
    var exception:ApplicationException? = null
) {
    val isLoading get() = exception == null && currencies == null
}