package com.hazem.currency_converter.data.remote.currency.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionHistoryResponse(
    val success:Boolean,
    @Json(name = "timeseries")
    val timeSeries:Boolean,
    @Json(name = "start_date")
    val startDate:String,
    @Json(name = "end_date")
    val endDate:String,
    val base:String,
    val rates:Map<String,Map<String,String>>
)