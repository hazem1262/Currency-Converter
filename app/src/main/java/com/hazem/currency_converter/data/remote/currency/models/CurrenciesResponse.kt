package com.hazem.androidmvistarter.data.remote.currency

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SymbolsResponse(val success:Boolean, val symbols:Map<String, String>)
