package com.berkay.feature.products.domain.model

data class SuggestedProduct(
    val id: String = "",
    val imageURL: String = "",
    val price: Double = 0.0,
    val name: String = "",
    val priceText: String = "",
    val shortDescription: String = "",
    val category: String = "",
    val unitPrice: Double = 0.0,
    val status: Int = 0
)
