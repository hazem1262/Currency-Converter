package com.hazem.currency_converter.presentation.currency.history.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionHistoryArgs(val from:String, val to:String) : Parcelable
