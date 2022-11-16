package com.hazem.currency_converter.data.remote.currency.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ConvertCurrencyResponse(val success:Boolean, val query:Query? = null, val info: Info? = null, val date:String? = null, val result:String?) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Info(val timestamp:String, val rate:String) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Query(val from:String, val to:String, val amount:Double) : Parcelable
