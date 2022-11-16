package com.hazem.currency_converter.utils

import androidx.annotation.VisibleForTesting
import com.hazem.currency_converter.data.remote.currency.models.ConvertCurrencyResponse
import com.hazem.currency_converter.data.remote.currency.models.CurrenciesResponse


@VisibleForTesting
internal fun createCurrenciesResponse(): CurrenciesResponse {
    return CurrenciesResponse(
        success = true,
        symbols = mapOf(
            "EGP" to "Egyptian pound",
            "AED" to "United Arab Emirates Dirham",
            "AFN" to "Afghan Afghani",
            "ALL" to "Albanian Lek",
            "AMD" to "Armenian Dram",
            "ANG" to "Netherlands Antillean Guilder",
        )
    )
}

internal fun createConvertResponse() : ConvertCurrencyResponse {
    return ConvertCurrencyResponse(
        success = true,
        result = "1.2",
    )
}