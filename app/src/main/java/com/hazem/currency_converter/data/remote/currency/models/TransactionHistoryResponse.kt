package com.hazem.androidmvistarter.data.remote.currency

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyHistoryData(
    val success:Boolean,
    val timeseries:Boolean,
    val start_date:String,
    val end_date:String,
    val base:String,
    val rates:Map<String,Map<String,String>>
)