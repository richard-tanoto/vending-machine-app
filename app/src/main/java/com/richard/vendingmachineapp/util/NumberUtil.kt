package com.richard.vendingmachineapp.util

import java.text.NumberFormat
import java.util.Locale

object NumberUtil {

    fun convertToRupiah(value: Number): String {
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(value).toString()
    }
}