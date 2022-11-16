package com.hazem.currency_converter.presentation.currency.history.mvi

import com.hazem.currency_converter.presentation.currency.history.model.CurrencyRate
import com.hazem.currency_converter.utils.network.ApplicationException

data class TransactionHistoryState(
    val historicalList: List<CurrencyRate>? = null,
    val otherCurrenciesList: List<CurrencyRate>? = null,
    var exception: ApplicationException? = null
) {
    val isLoading get() = exception == null && historicalList == null
}