package com.hazem.currency_converter.presentation.currency.converter

import androidx.lifecycle.ViewModel
import com.hazem.currency_converter.data.remote.currency.CurrencyRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepositoryContract
): ViewModel() {

}