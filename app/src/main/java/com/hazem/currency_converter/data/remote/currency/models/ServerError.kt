package com.hazem.currency_converter.data.remote.currency.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerError(val code:Int, val type:String, val info:String)