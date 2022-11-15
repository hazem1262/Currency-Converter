package com.hazem.currency_converter.presentation.currency.converter.mapper

import com.hazem.currency_converter.data.remote.currency.models.CurrenciesResponse
import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel

object CurrencyUiMapper {
    fun toCurrencyUiModel(
        response: CurrenciesResponse,
    ): List<CurrencyUiModel> {
        return response.symbols.map {
            currencyMap -> CurrencyUiModel(acronym = currencyMap.key, name = currencyMap.value)
        }
    }
}