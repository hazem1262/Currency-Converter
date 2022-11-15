package com.hazem.currency_converter.utils.network

object NetworkUtils {
    const val BASE_URL = "https://api.apilayer.com/"
    object EndPoints {
        const val SYMBOLS = "fixer/symbols"
        const val COVERT = "fixer/convert"
        const val HISTORY = "fixer/timeseries"
    }
}