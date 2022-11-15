package com.hazem.currency_converter.data.remote.currency

import com.hazem.currency_converter.data.remote.currency.models.ConvertCurrencyResponse
import com.hazem.currency_converter.data.remote.currency.models.CurrenciesResponse
import com.hazem.currency_converter.data.remote.currency.models.TransactionHistoryResponse
import com.hazem.currency_converter.utils.network.NetworkUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET(NetworkUtils.EndPoints.SYMBOLS)
    suspend fun getAvailableCurrencies(): Response<CurrenciesResponse>

    @GET(NetworkUtils.EndPoints.COVERT)
    suspend fun convertCurrency(@Query("from") from:String, @Query("to") to:String, @Query("amount") amount:Double): Response<ConvertCurrencyResponse>

    @GET(NetworkUtils.EndPoints.HISTORY)
    suspend fun getTransactionHistoricalData(@Query("start_date") startDate:String, @Query("end_date") endDate:String, @Query("base") base:String): Response<TransactionHistoryResponse>
}