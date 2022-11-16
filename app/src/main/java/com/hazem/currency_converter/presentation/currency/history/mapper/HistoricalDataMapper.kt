package com.hazem.currency_converter.presentation.currency.history.mapper

import com.hazem.currency_converter.data.remote.currency.models.TransactionHistoryResponse
import com.hazem.currency_converter.presentation.currency.history.model.CurrencyRate
import com.hazem.currency_converter.utils.date.getTodayFormatted

object HistoricalDataMapper {
    fun toTransactionHistoryData(response: TransactionHistoryResponse, currencyTo:String):List<CurrencyRate> {
        val currencyRates = arrayListOf<CurrencyRate>()
        response.rates.entries.forEach {
            val currencyToRate = it.value[currencyTo]
            currencyToRate?.let { rate ->
                currencyRates.add(
                    CurrencyRate(
                        currencyFrom = response.base,
                        currencyTo = currencyTo,
                        rate = rate,
                        date = it.key
                    )
                )
            }
        }
        return currencyRates
    }

    fun toOtherCurrenciesData(response: TransactionHistoryResponse):List<CurrencyRate> {
        val currencyRates = arrayListOf<CurrencyRate>()
        val today = getTodayFormatted()
        val todayTransactions = response.rates.entries.find {
            it.key == today
        }
        todayTransactions?.value?.entries?.take(10)?.forEach {
            currencyRates.add(
                CurrencyRate(
                    currencyFrom = response.base,
                    currencyTo = it.key,
                    rate = it.value,
                    date = ""
                )
            )
        }
        return currencyRates
    }
}