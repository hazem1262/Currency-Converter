package com.hazem.currency_converter.data.remote.currency.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrenciesResponse(val success:Boolean, val symbols:Map<String, String>)
