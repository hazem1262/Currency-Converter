package com.hazem.currency_converter.presentation.currency.history

import androidx.lifecycle.viewModelScope
import com.hazem.currency_converter.base.BaseViewModel
import com.hazem.currency_converter.data.remote.currency.CurrencyRepositoryContract
import com.hazem.currency_converter.presentation.currency.history.mapper.HistoricalDataMapper
import com.hazem.currency_converter.presentation.currency.history.model.TransactionHistoryArgs
import com.hazem.currency_converter.presentation.currency.history.mvi.TransactionHistoryState
import com.hazem.currency_converter.utils.date.getDayBeforeYesterdayFormatted
import com.hazem.currency_converter.utils.date.getTodayFormatted
import com.hazem.currency_converter.utils.network.ApplicationException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepositoryContract
): BaseViewModel() {
    val uiState = MutableStateFlow(TransactionHistoryState())

    fun getHistoricalData(transactionHistoryArgs: TransactionHistoryArgs) {
        viewModelScope.launch {
            val currencyHistoryDataResponse = currencyRepository.getTransactionHistoricalData(startDate = getDayBeforeYesterdayFormatted(), endDate = getTodayFormatted(), base = transactionHistoryArgs.from)
            val historicalList = HistoricalDataMapper.toTransactionHistoryData(currencyHistoryDataResponse, transactionHistoryArgs.to)
            val otherCurrenciesData = HistoricalDataMapper.toOtherCurrenciesData(currencyHistoryDataResponse)

            uiState.value = uiState.value.copy(
                historicalList = historicalList,
                otherCurrenciesList = otherCurrenciesData
            )
        }
    }

    override fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            uiState.value = uiState.value.copy(exception = throwable)
        }
    }
}