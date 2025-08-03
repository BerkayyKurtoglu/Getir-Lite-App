package com.berkay.feature.common.domain

data class CardCacheModel(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val count: Int = 0,
){
    val totalPriceString: String
        get() {
            val result = price * count
            return result.toString()
        }
}
