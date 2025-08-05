package com.berkay.feature.common.domain

import java.util.Locale

data class CartCacheModel(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val attribute: String = "",
    val price: Double = 0.0,
    val count: Int = 0,
){
    val totalPrice: Double
        get() {
            return price * count
        }
    val priceText: String
        get() {
            return String.format(Locale("tr", "TR"), "%.2f", this)
        }
}
