package com.berkay.presentation

import java.util.Locale

fun Double.formatAsPriceTr(): String {
    return "₺" + String.format(Locale("tr", "TR"), "%.2f", this)
}
