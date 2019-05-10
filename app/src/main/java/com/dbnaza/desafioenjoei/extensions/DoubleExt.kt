package com.dbnaza.desafioenjoei.extensions

import java.text.NumberFormat
import java.util.*

fun Double.formatCurrency() : String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val convertToInt = this.toInt()
    if (this - convertToInt == 0.0) {
        format.maximumFractionDigits = 0
    }
    format.currency = Currency.getInstance("BRL")
    return format.format(this)
}
