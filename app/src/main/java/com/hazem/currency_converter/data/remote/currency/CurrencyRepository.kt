package com.hazem.currency_converter.data.remote.currency

import com.hazem.currency_converter.base.BaseRepository
import com.hazem.currency_converter.data.remote.currency.models.ConvertCurrencyResponse
import com.hazem.currency_converter.data.remote.currency.models.CurrenciesResponse
import com.hazem.currency_converter.data.remote.currency.models.TransactionHistoryResponse
import com.hazem.currency_converter.utils.coroutines.ContextProviders
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CurrencyRepository @Inject constructor(
    contextProviders: ContextProviders,
    private val currencyService: CurrencyService
): BaseRepository(contextProviders), CurrencyRepositoryContract {
    override suspend fun getAvailableCurrencies(): CurrenciesResponse {
        return launchBlock {
            return@launchBlock currencyService.getAvailableCurrencies()
        }
    }

    override suspend fun covertCurrency(
        from: String,
        to: String,
        amount: Double
    ): ConvertCurrencyResponse {
        return launchBlock {
            return@launchBlock currencyService.convertCurrency(from = from, to = to, amount = amount)
        }
    }

    override suspend fun getTransactionHistoricalData(
        startDate: String,
        endDate: String,
        base: String
    ): TransactionHistoryResponse {
        return launchBlock {
            return@launchBlock currencyService.getTransactionHistoricalData(startDate = startDate, endDate = endDate, base = base)
        }
    }
}