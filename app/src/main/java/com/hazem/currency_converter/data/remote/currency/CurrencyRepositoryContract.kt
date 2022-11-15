package com.hazem.currency_converter.data.remote.currency

import com.hazem.currency_converter.data.remote.currency.models.ConvertCurrencyResponse
import com.hazem.currency_converter.data.remote.currency.models.CurrenciesResponse
import com.hazem.currency_converter.data.remote.currency.models.TransactionHistoryResponse
import retrofit2.http.Query

interface CurrencyRepositoryContract {
    suspend fun getAvailableCurrencies(): CurrenciesResponse
    suspend fun covertCurrency(@Query("from") from:String, @Query("to") to:String, @Query("amount") amount:Double): ConvertCurrencyResponse
    suspend fun getTransactionHistoricalData(@Query("start_date") startDate:String, @Query("end_date") endDate:String, @Query("base") base:String): TransactionHistoryResponse
}