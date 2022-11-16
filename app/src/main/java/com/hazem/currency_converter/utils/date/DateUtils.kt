package com.hazem.currency_converter.utils.date

import java.text.SimpleDateFormat
import java.util.*

fun getTodayFormatted() : String {
    val sdf = SimpleDateFormat(SERVER_PATTERN, Locale("En"))
    return sdf.format(Calendar.getInstance().time)
}

fun getDayBeforeYesterdayFormatted() : String {
    val sdf = SimpleDateFormat(SERVER_PATTERN, Locale("En"))
    val c = Calendar.getInstance()
    c.add(Calendar.DATE, -2)
    return sdf.format(c.time)
}

const val SERVER_PATTERN = "yyyy-MM-dd"